package com.rent.tool.dto;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;

public class ServiceResponse {

	public ServiceResponse(String code,String message, List<JSONObject> details) {
		super();
		this.message = message;
		this.details = details;
		this.code = code;
	}

	private String message;

	private String code;

	private List<JSONObject> details;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<JSONObject> getDetails() {
		return details;
	}

	public void setDetails(List<JSONObject> details) {
		this.details = details;
	}

}
