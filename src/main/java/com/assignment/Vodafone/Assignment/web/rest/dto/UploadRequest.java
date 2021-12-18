package com.assignment.Vodafone.Assignment.web.rest.dto;

public class UploadRequest {
	public String filepath;

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public String toString() {
		return "UploadRequest [filepath=" + filepath + "]";
	}
	

}
