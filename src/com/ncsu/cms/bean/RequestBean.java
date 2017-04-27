package com.ncsu.cms.bean;
import java.io.Serializable;
public class RequestBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String requestId;
	private String userId;
	private String studentName;
	private String creditCount;
	private String adminName;
	private String requestDate;
	private String updateDate;
	private String offeringId;
	private String status;
	
	
	public RequestBean(String requestId, String userId, String studentName, String creditCount, String adminName,
			String requestDate, String updateDate, String offeringId, String status) {
		super();
		this.requestId = requestId;
		this.userId = userId;
		this.studentName = studentName;
		this.creditCount = creditCount;
		this.adminName = adminName;
		this.requestDate = requestDate;
		this.updateDate = updateDate;
		this.offeringId = offeringId;
		this.status = status;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCreditCount() {
		return creditCount;
	}
	public void setCreditCount(String creditCount) {
		this.creditCount = creditCount;
	}

	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getOfferingId() {
		return offeringId;
	}
	public void setOfferingId(String offeringId) {
		this.offeringId = offeringId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
		

}
