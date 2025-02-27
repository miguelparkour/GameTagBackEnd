package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
	private String _id;	//empty (fill with mongoDb)
	private String name;					
	private String slug;			
	private String franchise;
	private String storyline;
	private String description;			
	private LocalDate release;

	// ------- searcheables props				
	private String collection;	
	private List<String> game_engines;
	private List<String> game_modes;
	private List<String> genres;
	private List<String> involved_companies;
	private List<String> platforms;
	private List<String> player_perspectives;
	private List<String> themes;
	// ------- searcheables props
	
	private List<Rating> ratings;
	private List<Link> links;
	private Media media;
	
	
	
	/*
	 * public Methods
	 */
	public List<String> emptyPropertiesName(){
		List<String> result = new ArrayList<String>();
		if(this.name == null) result.add("name");
		if(this.storyline == null) result.add("storyline");
		if(this.description == null) result.add("description");
		if(this.release == null) result.add("release");
		return result;
	}
	public Map<String, List<String>> listPropertiesSercheables(){
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		if(this.game_engines != null) result.put("game_engines",this.getEngines());
		if(this.game_modes != null) result.put("game_modes",this.getGame_modes());
		if(this.genres != null) result.put("genres",this.getGenres());
		//if(this.involved_companies != null) result.put("involved_companies",this.getInvolved_companies());
		if(this.platforms != null) result.put("platforms",this.getPlatforms());
		if(this.player_perspectives != null) result.put("player_perspectives",this.getPlayer_perspectives());
		if(this.themes != null) result.put("themes",this.getThemes());
		return result;
	}
	public Map<String, String> stringPropertiesSercheables(){
		Map<String, String> result = new HashMap<String, String>();
		if(this.collection != null) result.put("collection",this.getCollection());
//		if(this.franchise != null) result.put("franchise",this.getFranchise());
		return result;
	}
	
	
	
	/*
	 * Constructors
	 */
	public Game() {super();}
	
	
	
	/*
	 * SubClases
	 */
	class Link {
		private String name;
		private String url;
		public Link() {}
		public Link(String name, String url) {
			super();
			this.name = name;
			this.url = url;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}	
	}
	class Rating{
		private String name;
		private int value;
		public Rating() {}
		public Rating(String name, int value) {
			super();
			this.name = name;
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
	}
	
	
	
	/*
	 * Getters and Setters
	 */
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	public String getFranchise() {
		return franchise;
	}
	public void setFranchise(String franchise) {
		this.franchise = franchise;
	}
	public String getStoryline() {
		return storyline;
	}
	public void setStoryline(String storyline) {
		this.storyline = storyline;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getRelease() {
		return release;
	}
	public void setRelease(LocalDate release) {
		this.release = release;
	}
	public List<String> getEngines() {
		return game_engines;
	}
	public void setEngines(List<String> engines) {
		this.game_engines = engines;
	}
	public List<String> getGenres() {
		return genres;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	public List<String> getPlatforms() {
		return platforms;
	}
	public void setPlatforms(List<String> platforms) {
		this.platforms = platforms;
	}
	public List<String> getThemes() {
		return themes;
	}
	public List<String> getGame_modes() {
		return game_modes;
	}
	public void setGame_modes(List<String> game_modes) {
		this.game_modes = game_modes;
	}
	public List<String> getInvolved_companies() {
		return involved_companies;
	}
	public void setInvolved_companies(List<String> involved_companies) {
		this.involved_companies = involved_companies;
	}
	public List<String> getPlayer_perspectives() {
		return player_perspectives;
	}
	public void setPlayer_perspectives(List<String> player_perspectives) {
		this.player_perspectives = player_perspectives;
	}
	public void setThemes(List<String> themes) {
		this.themes = themes;
	}
	public List<Rating> getRatings() {
		return ratings;
	}
	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public Media getMedia() {
		return media;
	}
	public void setMedia(Media media) {
		this.media = media;
	}
	public boolean addRatingItem(String name, int value) {
		if (this.ratings == null) this.ratings = new ArrayList<Game.Rating>();
		return this.ratings.add(new Rating(name, value));
	}
	public boolean addLinkItem(String label, String url) {
		if(this.links == null) this.links = new ArrayList<Game.Link>();
		if(url == null || url.equals("")) return false;
		return this.links.add(new Link(label, url));
	}
}