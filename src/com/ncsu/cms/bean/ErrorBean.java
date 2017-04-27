package com.ncsu.cms.bean;

import java.io.Serializable;

public class ErrorBean  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String errorMessage;
	public String errorCode;
	public static final String SUCCESS = "SUCCESS";
	public static final String ERROR = "ERROR";
	
	public ErrorBean(String errorMessage, String errorCode) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
	
	public ErrorBean() {
		this.errorMessage = SUCCESS;
		this.errorCode = "0";
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
