package com.assignment.Vodafone.Assignment.exception;

public class DeviceApiResponse {
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DeviceApiResponse() {

	}

	public DeviceApiResponse(String description) {
		super();
		this.description = description;
	}

	@Override
	public String toString() {
		return "CustomErrorResponse [description=" + description + "]";
	}

}
