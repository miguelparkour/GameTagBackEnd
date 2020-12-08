package connection;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.eq;

import models.Tag;

public class TagsDB {
	
	
	// ::::::::::::::: Sets :::::::::::::::::
	public static void setTags(List<Tag> tags) {
		List<Document> docs = new ArrayList<Document>();
		List<Tag> mongoTags = getTags();
		for(Tag tag : tags) {
			if(!mongoTags.contains(tag)) {
				docs.add(Utils.toDocument(tag));				
			}
		}
		if(docs.size() > 0) {
			MongoConnection.getMongoClient()
						.getDatabase("base")
						.getCollection("tags")
						.insertMany(docs);
			
			System.out.println("insertando Tags");
		}
	}
	
	
	// ::::::::::::::: Gets :::::::::::::::::
	public static List<Tag> getTags() {
		//Contenedor de tags
		List<Tag> tags = new ArrayList<Tag>();

		// Database Conexion
		MongoCursor<Document> mongoTags = MongoConnection.getMongoClient()
												.getDatabase("base")
												.getCollection("tags")
												.find()
												.iterator();
		
		System.out.println("obteniendo tags");
		while (mongoTags.hasNext()) {
			Document document = (Document) mongoTags.next();
			Tag tag = (Tag) Utils.documentTo(document,Tag.class);
			tags.add(tag);
		}
		return tags;
	}

	// ::::::::::::::: Others :::::::::::::::::
	// Only for Testing uses
	public static void getSpecificTag() {
		Bson filter = eq("_id","themes:1");
		Document doc = MongoConnection.getMongoClient()
									.getDatabase("base")
									.getCollection("tags")
									.find(filter).first();
		
		System.out.println("este no deería salir");
		if(doc != null) {
			Tag tag = (Tag) Utils.documentTo(doc, Tag.class);
			System.out.println(tag.toString());
		}else {
			System.out.println("Ese tag no estág en la base de datos");
		}
	}
}
