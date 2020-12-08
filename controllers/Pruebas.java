package controllers;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import connection.GamesDB;
import connection.MongoConnection;
import connection.TagsDB;
import models.Game;
import services.IgdbCalls;
import services.RawgioCalls;
import utils.IgdbData;
import utils.RawgioData;

public class Pruebas {
	
	public static void main(String[] args) {
		
//		MongoClient mongoClient = MongoConnection.initClient();
//
//		MongoDatabase base1 = mongoClient.getDatabase("base1");
//		MongoDatabase base2 = mongoClient.getDatabase("base2");
//
//		MongoCollection<Document> coll1 = base1.getCollection("coll1");
//		MongoCollection<Document> coll2 = base1.getCollection("coll2");
//		MongoCollection<Document> coll3 = base2.getCollection("coll3");
//		
//		Document doc = new Document();
//		doc.append("nombre", "Miguel");
//		coll2.insertOne(doc);
//		coll1.insertOne(doc);
//		coll3.insertOne(doc);
//		
//		System.out.println("END");
		
		
	}
	
	
	
	
	
	
	
	

	
	
	public static void main2(String[] args) {		
		String fields = "fields alternative_names.name,collection.name,cover.url,first_release_date,franchise.name,game_engines.name,game_modes.name,genres.name,involved_companies.company.name,name,platforms.name,player_perspectives.name,rating,screenshots.url,slug,storyline,summary,themes.name,url,websites.url,websites.category;";
		String body = fields + "limit 1;where follows != null & name != null & parent_game = null & alternative_names!=null&collection!=null&cover!=null&franchise!=null&game_engines!=null&involved_companies!=null&name!=null&platforms!=null;sort follows desc;";
		String json = new IgdbCalls().getGames(body);
		System.out.println(json);
		IgdbData igdb = new IgdbData(json);
		RawgioCalls r = new RawgioCalls();
		List<Game> games = new ArrayList<Game>();
		for(Game game : igdb.games) {
			String rawgjson = r.getGame(game.getSlug());
			games.add(RawgioData.init(rawgjson, game));
		}
		GamesDB.setGames(games);
		TagsDB.setTags(igdb.tags);
	}
}

