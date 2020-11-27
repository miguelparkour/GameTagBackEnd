package controllers;

import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import connection.GamesDB;
import connection.TagsDB;
import models.Game;
import models.Tag;

public class Home {

	public static String getTags() {
		return new Gson().toJson(TagsDB.getTags(),new TypeToken<List<Tag>>(){}.getType());
	}
	public static String getGames(String body) {
		
		// obtenemos el array desde el body
		JsonObject obj = (JsonObject) new Gson().fromJson(body, JsonObject.class);
		System.out.println(obj.get("tags").toString());
		
		// lo mapeamos con una lista de Tags
		Type listType = new TypeToken<ArrayList<Tag>>(){}.getType();
		List<Tag> tags = new Gson().fromJson(obj.get("tags").toString(), listType);
		
		// recuperamos los juegos que coincidan en mongo
		List<Game> games = GamesDB.getGames(tags);
		
		// TODO: si mongo devuelve null o menos de 10 juegos buscar los juegos en IGDB
		
		
		// Parsear el resultado a un String para devolver la respuesta
		return new Gson().toJson(games,new TypeToken<List<Game>>(){}.getType());
	}
}
