package com.ncsu.cms.bean;

public class RequestListBean {
	
	private static final long serialVersionUID = 1L;
	private String requestId;
	private String courseId;
	private String requestDate;
	private String OfferingId;
	private String status;
	public RequestListBean(String requestId, String courseId, String requestDate, String offeringId, String status) {
		super();
		this.requestId = requestId;
		this.courseId = courseId;
		this.requestDate = requestDate;
		OfferingId = offeringId;
		this.status = status;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getOfferingId() {
		return OfferingId;
	}
	public void setOfferingId(String offeringId) {
		OfferingId = offeringId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
