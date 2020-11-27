package controllers;

import java.util.ArrayList;
import java.util.List;

import connection.GamesDB;
import models.Game;
import models.Tag;

public class Pruebas {
	public static void amain(String[] args) {
		List<Tag> tags = new ArrayList<Tag>();
		tags.add(new Tag("1","Shooter","genres"));
//		tags.add(new Tag("1","PC (Microsoft Windows)","platforms"));
//		tags.add(new Tag("1","Single player","modes"));
		List<Game> games = GamesDB.getGames(tags);
		for (Game game : games) {
			System.out.println(game.getSlug());
		}
	}
	public static void main(String[] args) {
//		{"tags":[{"_id":"testID1","type":"typeTest","name":"fakename"},{"_id":"testID2","type":"typeTest2","name":"fakename2"}]}
		String response = "{\"tags\":[{\"_id\":\"testID1\",\"type\":\"typeTest\",\"name\":\"fakename\"},{\"_id\":\"testID2\",\"type\":\"typeTest2\",\"name\":\"fakename2\"}]}";
		Home.getGames(response);
	
	}
}

