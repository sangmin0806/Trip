package com.ssafy.trip.model;

import java.util.List;

import com.ssafy.board.model.FileInfoDto;

public class TripDto {
	private int id;
	private int contentId;
	private int contentTypeId;
	private String typeName;
	private int thumbnailImageId;
	private String title;
	private String tel;
	private String description;
	private String imageUrl;

	private int sidoCode;
	private int gugunCode;
	private String sidoName;
	private String gugunName;
	private String address;
	private double latitude;
	private double longitude;
	
	
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "TripDto [id=" + id + ", contentId=" + contentId + ", contentTypeId=" + contentTypeId + ", typeName="
				+ typeName + ", thumbnailImageId=" + thumbnailImageId + ", title=" + title + ", tel=" + tel
				+ ", description=" + description + ", sidoCode=" + sidoCode + ", gugunCode=" + gugunCode + ", sidoName="
				+ sidoName + ", gugunName=" + gugunName + ", address=" + address + ", latitude=" + latitude + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getThumbnailImageId() {
		return thumbnailImageId;
	}
	public void setThumbnailImageId(int thumbnailImageId) {
		this.thumbnailImageId = thumbnailImageId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}
	public int getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	

}
