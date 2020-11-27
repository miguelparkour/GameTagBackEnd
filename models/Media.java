package models;

import java.util.List;

public class Media {
	private String cover;
	private String background;
	private String videoPreview;
	private String youtubeUrl;
	private String video320;
	private String video640;
	private String videoFull;
	private List<String> images;
	public Media() {
		super();
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
		return "Media [\n\tcover=" + cover + "\n\tbackground=" + background + "\n\tvideoPreview=" + videoPreview
				+ "\n\tyoutubeUrl=" + youtubeUrl + "\n\tvideo320=" + video320 + "\n\tvideo640=" + video640
				+ "\n\tvideoFull=" + videoFull + "\n\timages=" + images + "]";
	}
}