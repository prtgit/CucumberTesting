package com.ncsu.cms.bean;

import java.io.Serializable;

public class CourseBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String departmentName;
	private String courseId;
	private String courseName;
	private String offeringId;
    

	

	public CourseBean(String departmentName, String courseId, String courseName, String offeringId) {
		super();
		this.departmentName = departmentName;
		this.courseId = courseId;
		this.courseName = courseName;
		this.offeringId = offeringId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getOfferingId() {
		return offeringId;
	}

	public void setOfferingId(String offeringId) {
		this.offeringId = offeringId;
	}	
}
