package connection;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;

import static com.mongodb.client.model.Filters.*;

import models.Game;
import models.Tag;

public class GamesDB {
	// ::::::::::::::: Sets :::::::::::::::::
	public static void setGames(List<Game> games) {
		List<Document> docs = new ArrayList<Document>();
		for (Game game : games) {
			if (!existInMongo(game)) {
				docs.add(Utils.toDocument(game));
			}
		}
		if (docs.size() > 0) {
			MongoClients.create().getDatabase("base").getCollection("games").insertMany(docs);
		}
	}

	// ::::::::::::::: Gets :::::::::::::::::
	public static boolean existInMongo(Game game) {
		Bson filter = eq("slug", game.getSlug());
		return MongoClients.create().getDatabase("base").getCollection("games").find(filter).first() != null;
	}

	public static List<Game> getGames(List<Tag> tags) {
		List<Game> games = new ArrayList<Game>();
		
		// lista de tags a un filtro
		List<Bson> bsonList = new ArrayList<Bson>();
		for(Tag tag : tags) {
			String field = tag.getType();
			String name = tag.getName().replace("(", "\\(").replace(")", "\\)");
			System.out.println("field: "+field+"   name: "+name);
			bsonList.add(regex(field,name,"i"));
		}
		int tam = tags.size();
		Bson[] reg = new Bson[tam];
		reg = bsonList.toArray(reg);
		Bson filter = and(reg);
		
		// Database Conexion
		FindIterable<Document> docs = MongoClients.create().getDatabase("base").getCollection("games").find(filter).limit(10);
		for (Document document : docs) {
			document.remove("_id");
			String a = document.toJson();
			Game game = new Gson().fromJson(a, Game.class);
			games.add(game);
		}
		
		return games;
	}
}
