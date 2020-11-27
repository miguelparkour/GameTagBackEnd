package utils;

import java.time.LocalDate;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import models.Game;
import models.Media;

public class RawgioData {
	
	public static Game init(String json, Game game) {
		
		if (json == null ) {
			JsonObject obj = new Gson().fromJson(json, JsonObject.class);
			System.out.println("Juego relleno");
			return chargeInfo(obj, game);
		} else {
			System.out.println("El juego no se ha podido rellenar con Rawg.io dado que no contiene dicho slug");
			return game;
		}
	}

	private static Game chargeInfo(JsonObject obj, Game game) {

		Media media = game.getMedia();
		
		// ratings
		String sMeta = (String) JsonData.getProperty(obj, "metacritic");
		if(sMeta != null && !sMeta.equals(""))
		game.addRatingItem("Metacritic", Integer.parseInt(sMeta));
		String sRaw = (String) JsonData.getProperty(obj, "rating");
		if(sRaw != null && !sRaw.equals("")) {
			game.addRatingItem("RawgIo", (int) Math.round(Float.parseFloat(sRaw) / obj.get("rating_top").getAsFloat() * 100));
		}
			
		
		
		// metacritic url
		game.addLinkItem("metacritic", (String) JsonData.getProperty(obj, "metacritic_url"));
		
		
		// background
		media.setBackground((String) JsonData.getProperty(obj, "background_image"));
		
		
		// video preview
		media.setVideoPreview((String) JsonData.getProperty(obj, "clip", "preview"));
		
		
		// Videos
		media.setVideoPreview((String) JsonData.getProperty(obj, "clip", "preview"));
		media.setYoutubeUrl("www.youtube.com/watch?v="+(String) JsonData.getProperty(obj, "clip", "video"));		
		media.setVideo320((String) JsonData.getProperty(obj, "clip", "clips", "360"));
		media.setVideo640((String) JsonData.getProperty(obj, "clip", "clips", "640"));
		media.setVideoFull((String) JsonData.getProperty(obj, "clip", "clips", "full"));
		
		// emptyProperties
		List<String> temp = game.emptyPropertiesName();
		for (String property : temp) {
			if (property.equals("name")) game.setName((String) JsonData.getProperty(obj, "name"));
			if (property.equals("storyline")) game.setName((String) JsonData.getProperty(obj, "description_raw"));
			if (property.equals("description")) game.setName((String) JsonData.getProperty(obj, "description_raw"));
			if (property.equals("release")) {
				String release = (String) JsonData.getProperty(obj, "released");
				if(release != null) {
					String[] sDate = release.split("-");
					if(sDate.length == 3) game.setRelease(LocalDate.of(Integer.parseInt(sDate[0]), Integer.parseInt(sDate[1]), Integer.parseInt(sDate[2])));
				}
			} 
		}
		
		
		game.setMedia(media);
		
		return game;
	}
}
