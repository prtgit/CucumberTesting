package com.ncsu.cms.bean;
import java.io.Serializable;
public class CourseListBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String courseId;
	private String courseName;
	private String departmentId;
	private String departmentDescription;
	private String maxCredits;
	private String minCredits;
	private String courseType;
	private String courseTypeDescription;
	private String classificationLevel;
	private String classificationLevelDescription;
	
	
	public CourseListBean(String courseId, String courseName, String departmentId, String departmentDescription,
			String maxCredits, String minCredits, String courseType, String courseTypeDescription,
			String classificationLevel, String classificationLevelDescription) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.departmentId = departmentId;
		this.departmentDescription = departmentDescription;
		this.maxCredits = maxCredits;
		this.minCredits = minCredits;
		this.courseType = courseType;
		this.courseTypeDescription = courseTypeDescription;
		this.classificationLevel = classificationLevel;
		this.classificationLevelDescription = classificationLevelDescription;
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
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentDescription() {
		return departmentDescription;
	}
	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}
	public String getMaxCredits() {
		return maxCredits;
	}
	public void setMaxCredits(String maxCredits) {
		this.maxCredits = maxCredits;
	}
	public String getMinCredits() {
		return minCredits;
	}
	public void setMinCredits(String minCredits) {
		this.minCredits = minCredits;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getCourseTypeDescription() {
		return courseTypeDescription;
	}
	public void setCourseTypeDescription(String courseTypeDescription) {
		this.courseTypeDescription = courseTypeDescription;
	}
	public String getClassificationLevel() {
		return classificationLevel;
	}
	public void setClassificationLevel(String classificationLevel) {
		this.classificationLevel = classificationLevel;
	}
	public String getClassificationLevelDescription() {
		return classificationLevelDescription;
	}
	public void setClassificationLevelDescription(String classificationLevelDescription) {
		this.classificationLevelDescription = classificationLevelDescription;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
