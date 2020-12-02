package controllers;

import java.util.ArrayList;
import java.util.List;

import connection.GamesDB;
import connection.TagsDB;
import models.Game;
import services.IgdbCalls;
import services.RawgioCalls;
import utils.IgdbData;
import utils.RawgioData;

public class Pruebas {
	public static void main(String[] args) {		
		String fields = "fields alternative_names.name,collection.name,cover.url,first_release_date,franchise.name,game_engines.name,game_modes.name,genres.name,involved_companies.company.name,name,platforms.name,player_perspectives.name,rating,screenshots.url,slug,storyline,summary,themes.name,url,websites.url,websites.category;";
		String body = fields + "limit 1;where alternative_names!=null&collection!=null&cover!=null&franchise!=null&game_engines!=null&involved_companies!=null&name!=null&platforms!=null;";
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

