package services;

import java.util.List;
import java.util.stream.Collectors;

import models.Game;
import models.Tag;

public class ApiQuery {

	public static String doQuery(List<Game> games, List<Tag> tags) {
		int size = 12 - games.size();
		
		// query
		String fields = "fields alternative_names.name,collection.name,cover.url,first_release_date,franchise.name,game_engines.name,game_modes.name,genres.name,involved_companies.company.name,name,platforms.name,player_perspectives.name,rating,screenshots.url,slug,storyline,summary,themes.name,url,websites.url,websites.category;";
		String exclude = excludeGames(games);
		String filter = tagsFilter(tags,exclude.isEmpty());
		String limit = "limit " + size;
		String where = "sort follows desc; where follows != null & name != null & parent_game = null & " + exclude + " " + filter + "; " + limit + ";";
		System.out.println(fields + where);
		return fields + where;
	}

	private static String excludeGames(List<Game> games) {
		String result = "";
		for (Game game : games) {
			result += "slug != \"" + game.getSlug() + "\" & ";
		}
		if(!result.isEmpty()) {
			result = result.substring(0, result.length() - 3);
		}
		return result;
	}

	private static String tagsFilter(List<Tag> tags, boolean deleteFirstChar) {
		String result = "";
		List<Tag> engines = tags.stream().filter(tag -> tag.getType().equals("game_engines")).collect(Collectors.toList());
		List<Tag> modes = tags.stream().filter(tag -> tag.getType().equals("game_modes")).collect(Collectors.toList());
		List<Tag> genres = tags.stream().filter(tag -> tag.getType().equals("genres")).collect(Collectors.toList());
		List<Tag> companies = tags.stream().filter(tag -> tag.getType().equals("involved_companies")).collect(Collectors.toList());
		List<Tag> platforms = tags.stream().filter(tag -> tag.getType().equals("platforms")).collect(Collectors.toList());
		List<Tag> perspectives = tags.stream().filter(tag -> tag.getType().equals("player_perspectives")).collect(Collectors.toList());
		List<Tag> themes = tags.stream().filter(tag -> tag.getType().equals("themes")).collect(Collectors.toList());
		List<Tag> collection = tags.stream().filter(tag -> tag.getType().equals("collection")).collect(Collectors.toList());
		result += chargeField(engines,"game_engines");
		result += chargeField(modes,"game_modes");
		result += chargeField(genres,"genres");
		result += chargeField(companies,"involved_companies");
		result += chargeField(platforms,"platforms");
		result += chargeField(perspectives,"player_perspectives");
		result += chargeField(themes,"themes");
		if(collection != null && !collection.isEmpty()) {
			result += "& collection.name = \"" + collection.get(0).getName() + "\" ";
		}
		if(!result.isEmpty()) {
			result = result.substring(0, result.length() - 1);
		}
		result = result.replace("&&", "&");
		if(deleteFirstChar) {
			result = result.substring(2, result.length());
		}
		return result;
	}
	private static String chargeField(List<Tag> field, String type) {
		String result = "";
		if(field != null && !field.isEmpty()) {
			result += "& "+ type +" = [";
			for(Tag tag : field) {
				result += tag.getIgdbId() + ",";
			}
			if(!result.isEmpty()) {
				result = result.substring(0, result.length() - 1);
			}
			result += "] &";
		}
		return result;
	}
}




























