package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import connection.GamesDB;
import connection.TagsDB;
import models.Game;
import models.Tag;
import services.ApiQuery;
import services.IgdbCalls;
import services.RawgioCalls;
import utils.IgdbData;
import utils.RawgioData;

public class Home {

	public static String firstCall() {
		Map<String,String> map = new HashMap<String, String>();
		map.put("games",getGames(""));
		map.put("tags",new Gson().toJson(TagsDB.getTags(),new TypeToken<List<Tag>>(){}.getType()));
		return new Gson().toJson(map,new TypeToken<Map<String,String>>(){}.getType());
	}
	public static String getGame(String slug) {
		return new Gson().toJson(GamesDB.getGame(slug), Game.class);
	}
	public static String getGames(String body) {
		
		// obtenemos el array desde el body
		JsonObject obj = (JsonObject) new Gson().fromJson(body, JsonObject.class);
		
		// lo mapeamos con una lista de Tags
		Type listType = new TypeToken<ArrayList<Tag>>(){}.getType();
		List<Tag> tags = new ArrayList<Tag>();
		if(body != null && !body.isEmpty())
		tags = new Gson().fromJson(obj.get("tags").toString(), listType);
		
		// recuperamos los juegos que coincidan con la lista de tags
		List<Game> games = GamesDB.getGames(tags);
		
		// si mongo devuelve null o menos de 10 juegos buscar los juegos en IGDB
		if (games == null || games.size() < 12) {
			List<Game> newGames = fillGameList(ApiQuery.doQuery(games, tags));
			
			games.addAll(newGames);
		}
		
		// Parsear el resultado a un String para devolver la respuesta
		return new Gson().toJson(games,new TypeToken<List<Game>>(){}.getType());
	}
	private static List<Game> fillGameList(String body) {
		String json = new IgdbCalls().getGames(body);
		IgdbData igdb = new IgdbData(json);
		RawgioCalls r = new RawgioCalls();
		List<Game> games = new ArrayList<Game>();
		for(Game game : igdb.games) {
			String rawgjson = r.getGame(game.getSlug());
			games.add(RawgioData.init(rawgjson, game));
		}
		if(games != null && !games.isEmpty())GamesDB.setGames(games);
		if(igdb.tags != null && !igdb.tags.isEmpty())TagsDB.setTags(igdb.tags);
		
		return games;
	}
}
