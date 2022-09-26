package com.ibm.microoservices.model;

public class UserRegistartionDTO {
	//private String username;
	//private String city;
	private String uuid;
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
