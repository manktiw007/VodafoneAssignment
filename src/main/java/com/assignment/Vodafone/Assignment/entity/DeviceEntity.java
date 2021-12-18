package com.assignment.Vodafone.Assignment.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DeviceEntity {

	@Id
	private Integer eventId;
	private String productId;
	private String latitude;
	private String longitude;
	private String battery;
	private String light;
	private String airplaneMode;
	private Timestamp dateTime;

	public DeviceEntity() {

	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public String getLight() {
		return light;
	}

	public void setLight(String light) {
		this.light = light;
	}

	public String getAirplaneMode() {
		return airplaneMode;
	}

	public void setAirplaneMode(String airplaneMode) {
		this.airplaneMode = airplaneMode;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public DeviceEntity(Integer eventId, String productId, String latitude, String longitude, String battery,
			String light, String airplaneMode, Timestamp dateTime) {
		super();
		this.eventId = eventId;
		this.productId = productId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.battery = battery;
		this.light = light;
		this.airplaneMode = airplaneMode;
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "DeviceEntity [eventId=" + eventId + ", productId=" + productId + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", battery=" + battery + ", light=" + light + ", airplaneMode="
				+ airplaneMode + ", dateTime=" + dateTime + "]";
	}

}
