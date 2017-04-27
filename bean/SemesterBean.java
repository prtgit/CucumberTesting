package com.ncsu.cms.bean;
import java.io.*;
public class SemesterBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String semesterId;
	private String semesterType;
	private String startDate;
	private String endDate;
	private String courseAddDeadline;
	private String courseDropDeadline;
	public SemesterBean(String semesterId, String semesterType, String startDate, String endDate,
			String courseAddDeadline, String courseDropDeadline) {
		super();
		this.semesterId = semesterId;
		this.semesterType = semesterType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.courseAddDeadline = courseAddDeadline;
		this.courseDropDeadline = courseDropDeadline;
	}
	public String getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}
	public String getSemesterType() {
		return semesterType;
	}
	public void setSemesterType(String semesterType) {
		this.semesterType = semesterType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCourseAddDeadline() {
		return courseAddDeadline;
	}
	public void setCourseAddDeadline(String courseAddDeadline) {
		this.courseAddDeadline = courseAddDeadline;
	}
	public String getCourseDropDeadline() {
		return courseDropDeadline;
	}
	public void setCourseDropDeadline(String courseDropDeadline) {
		this.courseDropDeadline = courseDropDeadline;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
