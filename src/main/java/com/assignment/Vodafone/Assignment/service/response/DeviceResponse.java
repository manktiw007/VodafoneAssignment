package com.assignment.Vodafone.Assignment.service.response;

import java.io.Serializable;

public class DeviceResponse implements Serializable {

	private String responseMessage;
	private Object deviceDetailsResponse;
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public Object getDeviceDetailsResponse() {
		return deviceDetailsResponse;
	}
	public void setDeviceDetailsResponse(Object deviceDetailsResponse) {
		this.deviceDetailsResponse = deviceDetailsResponse;
	}
	
	
	
}
