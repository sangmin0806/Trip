package com.ssafy.trip.model;

public class TripListDto {
	private int id;
	private int userId;
	private String title;
	private String description;
	private String date;
	private int thumbnailImageId;
	private String url;
	public int getThumbnailImageId() {
		return thumbnailImageId;
	}
	public void setThumbnailImageId(int url) {
		this.thumbnailImageId = url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "TripListDto [id=" + id + ", user_id="  + ", title=" + title + ", description=" + description
				+ "]";
	}
	
}
