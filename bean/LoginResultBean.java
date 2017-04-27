package com.ncsu.cms.bean;

import java.io.Serializable;

public class LoginResultBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ErrorBean errorData;
	private String userId;
	private String role;
	
	public LoginResultBean() {
	}
	
	public LoginResultBean(ErrorBean errorData, String userId, String role) {
		super();
		this.errorData = errorData;
		this.userId = userId;
		this.role = role;
	}

	public ErrorBean getErrorData() {
		return errorData;
	}
	public void setErrorData(ErrorBean errorData) {
		this.errorData = errorData;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
