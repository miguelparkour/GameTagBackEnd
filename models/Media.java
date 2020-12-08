package models;

import java.util.ArrayList;
import java.util.List;

public class Media {
	private String cover;
	private String header;
	private String background;
	private String videoPreview;
	private String youtubeUrl;
	private String video320;
	private String video640;
	private String videoFull;
	private List<String> images;
	private List<String> movies;

	public boolean addImage(String image) {
		if(this.images == null) this.images = new ArrayList<String>();
		return this.images.add(image);
	}
	public boolean addImages(List<String> images) {
		if(this.images == null) this.images = new ArrayList<String>();
		return this.images.addAll(images);
	}
	public boolean addMovie(String movie) {
		if(this.movies == null) this.movies = new ArrayList<String>();
		return this.movies.add(movie);
	}
	public boolean addMovies(List<String> movies) {
		if(this.movies == null) this.movies = new ArrayList<String>();
		return this.movies.addAll(movies);
	}
	public Media() {
		super();
	}
	public List<String> getMovies() {
		return movies;
	}
	public void setMovies(List<String> movies) {
		this.movies = movies;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public String getVideoPreview() {
		return videoPreview;
	}
	public void setVideoPreview(String videoPreview) {
		this.videoPreview = videoPreview;
	}
	public String getYoutubeUrl() {
		return youtubeUrl;
	}
	public void setYoutubeUrl(String youtubeUrl) {
		if(!youtubeUrl.equals("www.youtube.com/watch?v=null"))
		this.youtubeUrl = youtubeUrl;
	}
	public String getVideo320() {
		return video320;
	}
	public void setVideo320(String video320) {
		this.video320 = video320;
	}
	public String getVideo640() {
		return video640;
	}
	public void setVideo640(String video640) {
		this.video640 = video640;
	}
	public String getVideoFull() {
		return videoFull;
	}
	public void setVideoFull(String videoFull) {
		this.videoFull = videoFull;
	}
	@Override
	public String toString() {
		return "Media [\n\tcover=" + cover + "\n\theader=" + header + "\n\tbackground=" + background
				+ "\n\tvideoPreview=" + videoPreview + "\n\tyoutubeUrl=" + youtubeUrl + "\n\tvideo320=" + video320
				+ "\n\tvideo640=" + video640 + "\n\tvideoFull=" + videoFull + "\n\timages=" + images + "\n\tmovies="
				+ movies;
	}
}