package connection;

import org.bson.Document;

import com.google.gson.Gson;

public class Utils {
	public static <T> Object documentTo(Document doc, Class<T> clase) {
		Gson gson = new Gson();
		String json = gson.toJson(doc);
		return gson.fromJson(json, clase);
	}
	public static Document toDocument(Object obj) {
		Gson gson = new Gson();
	    String json = gson.toJson(obj);
	    return Document.parse(json);
	}
}
