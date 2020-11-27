package utils;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import models.Game;
import models.Media;
import models.Tag;

public class IgdbData {
	private JsonObject obj;
	public List<Game> games;
	public List<Tag> tags;
	
	public IgdbData(String gamesJson) {

		Iterator<JsonElement> arrObj = new Gson().fromJson(gamesJson, JsonArray.class).iterator();
		this.games = new ArrayList<Game>();
		while (arrObj.hasNext()) {
			Game game = IgdbJsonToGame(arrObj.next().getAsJsonObject());
			if(this.games.add(game)) {
				if(tags == null) tags = new ArrayList<Tag>();
				List<Tag> temp = getTags(game);
				for(Tag tag : temp) {
					if(!tags.contains(tag)) tags.add(tag);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Game IgdbJsonToGame(JsonObject JsonObj) {
		
		obj = JsonObj;
		
		Game game = new Game();
		Media media = new Media();
		
		// String properties
		game.setName((String) JsonData.getProperty(obj,"name"));
		game.setSlug((String) JsonData.getProperty(obj,"slug"));
		game.setStoryline((String) JsonData.getProperty(obj,"storyline"));
		game.setDescription((String) JsonData.getProperty(obj,"summary"));
		game.setCollection((String) JsonData.getProperty(obj,"collection","name"));
		game.setFranchise((String) JsonData.getProperty(obj,"franchise","name"));
		media.setCover((String) JsonData.getProperty(obj,"cover","url"));
		
		// LocalDate property
		String release = (String) JsonData.getProperty(obj,"first_release_date");
		if(release != null)
		game.setRelease(Instant
							.ofEpochSecond(Long.parseLong(release))
							.atZone(ZoneId.systemDefault())
							.toLocalDate());
		
		// List<String> properties
		game.setEngines((List<String>) JsonData.getProperty(obj,"game_engines","name"));
		game.setModes((List<String>) JsonData.getProperty(obj,"game_modes","name"));
		game.setGenres((List<String>) JsonData.getProperty(obj,"genres","name"));
		game.setCompanies((List<String>) JsonData.getProperty(obj,"involved_companies","company","name"));
		game.setPlatforms((List<String>) JsonData.getProperty(obj,"platforms","name"));
		game.setPerspectives((List<String>) JsonData.getProperty(obj,"player_perspectives","name"));
		game.setThemes((List<String>) JsonData.getProperty(obj,"themes","name"));
		media.setImages((List<String>) JsonData.getProperty(obj,"screenshots","url"));
		
		
		// List<Rating> property
		if(obj.get("rating") != null)
		game.addRatingItem("igdb", (int)Math.round(obj.get("rating").getAsDouble()));
		
		// List< > property
		if (obj.get("websites") != null) {
			String[] labels = "official,wikia,wikipedia,facebook,twitter,twitch,unkwn,instagram,youtube,iphone,ipad,android,steam,reddit,itch,epicgames,gog,discord".split(",");
			List<String> keys = (List<String>) JsonData.getProperty(obj,"websites","category");
			List<String> urls = (List<String>) JsonData.getProperty(obj,"websites","url");
			if(keys.size() == urls.size()) {
				for (int i = 0 ; i < keys.size(); i++) {
					int pos = Integer.parseInt(keys.get(i))-1;
					game.addLinkItem(labels[pos], urls.get(i));
				}
			}
		}
		
		// Media property
		game.setMedia(media);
		
		
		// return the game
		return game;
	}
	@SuppressWarnings("unchecked")
	private List<Tag> getTags (Game game){
		List<Tag> result = new ArrayList<Tag>();
		
		Map<String, List<String>> map = game.propertiesSercheables();
		for (Entry<String, List<String>> entry : map.entrySet()) {
			List<String> values =  entry.getValue();
			List<String> ids = (List<String>) JsonData.getProperty(obj, entry.getKey(),"id");
			if(ids.size() == values.size()) {
				for (int i = 0; i < ids.size(); i++) {
					result.add(new Tag(ids.get(i),values.get(i),entry.getKey()));
				}
			}
		}
		
		return result;
	}
}