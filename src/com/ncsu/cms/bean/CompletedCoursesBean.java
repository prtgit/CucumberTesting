package com.ncsu.cms.bean;
import java.io.Serializable;
public class CompletedCoursesBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L ;
	private String departmentName;
	private String courseId;
	private String courseName;
	private String grade;
	public CompletedCoursesBean(String departmentName, String courseId, String courseName, String grade) {
		super();
		this.departmentName = departmentName;
		this.courseId = courseId;
		this.courseName = courseName;
		this.grade = grade;
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
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	

}
