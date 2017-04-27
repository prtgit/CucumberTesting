package com.ncsu.cms.bean;

import java.io.Serializable;
import java.util.List;

public class CurrentCourseBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String departmentName;
	private String courseId;
	private String courseName;
	private String offeringId;
	private LocationBean location;
    private List<ScheduleBean> scheduleList;
    private List<FacultyBean> facultyList;
    
	public CurrentCourseBean(String departmentName, String courseId, String courseName, String offeringId,
			LocationBean location, List<ScheduleBean> scheduleList, List<FacultyBean> facultyList) {
		super();
		this.departmentName = departmentName;
		this.courseId = courseId;
		this.courseName = courseName;
		this.offeringId = offeringId;
		this.location = location;
		this.scheduleList = scheduleList;
		this.facultyList = facultyList;
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
	public LocationBean getLocation() {
		return location;
	}
	public void setLocation(LocationBean location) {
		this.location = location;
	}
	public List<ScheduleBean> getScheduleList() {
		return scheduleList;
	}
	public void setScheduleList(List<ScheduleBean> scheduleList) {
		this.scheduleList = scheduleList;
	}
	public List<FacultyBean> getFacultyList() {
		return facultyList;
	}
	public void setFacultyList(List<FacultyBean> facultyList) {
		this.facultyList = facultyList;
	}
}
