package com.assignment.Vodafone.Assignment.entity;

import com.opencsv.bean.CsvBindByPosition;

public class DeviceData {

	@CsvBindByPosition(position = 1)
	private Integer eventId;
	@CsvBindByPosition(position = 2)
	private String productId;
	@CsvBindByPosition(position = 3)
	private String latitude;
	@CsvBindByPosition(position = 4)
	private String longitude;
	@CsvBindByPosition(position = 5)
	private String battery;
	@CsvBindByPosition(position = 6)
	private String light;
	@CsvBindByPosition(position = 7)
	private String airplaneMode;
	@CsvBindByPosition(position = 0)
	private Long dateTime;

	public DeviceData() {

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

	public Long getDateTime() {
		return dateTime;
	}

	public void setDateTime(Long dateTime) {
		this.dateTime = dateTime;
	}

	public DeviceData(Integer eventId, String productId, String latitude, String longitude, String battery,
			String light, String airplaneMode, Long dateTime) {
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
		return "DeviceData [eventId=" + eventId + ", productId=" + productId + ", latitude=" + latitude + ", longitude="
				+ longitude + ", battery=" + battery + ", light=" + light + ", airplaneMode=" + airplaneMode
				+ ", dateTime=" + dateTime + "]";
	}

}
