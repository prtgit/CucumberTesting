package com.ncsu.cms.bean;
import java.io.Serializable;
public class EnrolledBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String offeringId;
	private String creditCount;
	private String grade;
	private String userId;
	
	public EnrolledBean(String offeringId, String creditCount, String grade, String userId) {
		super();
		this.offeringId = offeringId;
		this.creditCount = creditCount;
		this.grade = grade;
		this.userId = userId;
	}
	public String getOfferingId() {
		return offeringId;
	}
	public void setOfferingId(String offeringId) {
		this.offeringId = offeringId;
	}
	public String getCreditCount() {
		return creditCount;
	}
	public void setCreditCount(String creditCount) {
		this.creditCount = creditCount;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
