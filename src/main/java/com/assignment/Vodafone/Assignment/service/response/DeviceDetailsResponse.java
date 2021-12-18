package com.assignment.Vodafone.Assignment.service.response;

public class DeviceDetailsResponse {

	   private String id;
	   private String name;
	   private String datetime;
	   private String longt;
	   private String lat;
	   private String status;
	   private String battery;
	   private String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getLongt() {
		return longt;
	}
	public void setLongt(String longt) {
		this.longt = longt;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "DeviceDetailsResponse [id=" + id + ", name=" + name + ", datetime=" + datetime + ", longt=" + longt
				+ ", lat=" + lat + ", status=" + status + ", battery=" + battery + ", description=" + description + "]";
	}
	   
	   
	   

}
