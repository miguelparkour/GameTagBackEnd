package connection;

import org.bson.Document;

import com.google.gson.Gson;

import models.Game;

public class Utils {
	public static <T> Object documentTo(Document doc, Class<T> clase) {
		Gson gson = new Gson();
		String json = gson.toJson(doc);
		return gson.fromJson(json, clase);
	}
	public static Game documentToGame(Document doc) {
		Game game = new Game();
		Gson gson = new Gson();
		doc.remove("_id");
		String json = gson.toJson(doc);
		return gson.fromJson(json, Game.class);
	}
	public static Document toDocument(Object obj) {
		Gson gson = new Gson();
	    String json = gson.toJson(obj);
	    return Document.parse(json);
	}
}
