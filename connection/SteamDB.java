package connection;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mongodb.client.MongoClients;

import models.Media;

public class SteamDB {

	public static Media fillMedia(Media media, String steamId) {

		System.out.println("SteamID: "+steamId);
		steamId = steamId.split("/")[0];
		
		if(steamId.equalsIgnoreCase("12120")) {
			System.out.println("Soy el san andreas");
		}
		
		Bson filter = eq("steam_appid",Integer.parseInt(steamId));
		Document doc = MongoClients.create().getDatabase("steam").getCollection("steam_media_data").find(filter).first();
		
		if(doc != null) {
			Gson gson = new Gson();
			//--- header
			String header = doc.getString("header_image");
			if(header != null && !header.isEmpty()) media.setHeader(header);
			//---
			
			//--- background
			String background = doc.getString("background");
			if(background != null && !background.isEmpty()) media.setBackground(background);
			//---
			
			//--- images
			try {
				String imagesJson = doc.getString("screenshots");
				JsonElement elem = gson.fromJson(imagesJson, JsonElement.class);
				if(elem != null && elem.isJsonArray()) {				
					JsonArray imagesArr = gson.fromJson(imagesJson, JsonArray.class);
					Iterator<JsonElement> ite = imagesArr.iterator();
					while (ite.hasNext()) {
						JsonObject obj = (JsonObject) ite.next();
						String image = obj.get("path_full").getAsString();
						if(image != null && !image.isEmpty()) {
							media.addImage(image);
						}
					}
				}
			} catch (Exception e) {
				System.out.println("ERROR al obtener los screenshots de Steam: " + e.getMessage());
			}
			//---
			
			//--- movies
			try {
				String moviesJson = doc.getString("movies");
				JsonElement elem = gson.fromJson(moviesJson, JsonElement.class);
				if(elem != null && elem.isJsonArray()) {				
					JsonArray moviesArr = gson.fromJson(moviesJson, JsonArray.class);
					Iterator<JsonElement> ite = moviesArr.iterator();
					while (ite.hasNext()) {
						JsonObject obj = (JsonObject) ite.next();
						obj = obj.get("webm").getAsJsonObject();
						String movie = obj.get("max").getAsString();
						if(movie != null && !movie.isEmpty()) {
							media.addMovie(movie);
						}
					}
				}
			} catch (Exception e) {
				System.out.println("ERROR al obtener las movies de Steam: " + e.getMessage());
			}
			//---
		}
		System.out.println("juego cargado en steam");
		System.out.println(media.toString());
		
		return media;
	}
}
