package com.ncsu.cms.bean;
import java.io.Serializable;
import java.util.List;
public class CourseOfferingListBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String courseOfferingId;
	private String courseId;
	private String classSize;
	private String waitlistSize;
	private String semesterId;
	private String locationId;
	private List<FacultyBean> faculty;
    private List<ScheduleBean> schedule;
    
	public CourseOfferingListBean(String courseOfferingId, String courseId, String classSize, String waitlistSize,
			String semesterId, String locationId, List<FacultyBean> faculty, List<ScheduleBean> schedule) {
		super();
		this.courseOfferingId = courseOfferingId;
		this.courseId = courseId;
		this.classSize = classSize;
		this.waitlistSize = waitlistSize;
		this.semesterId = semesterId;
		this.locationId = locationId;
		this.faculty = faculty;
		this.schedule = schedule;
	}
	public String getCourseOfferingId() {
		return courseOfferingId;
	}
	public void setCourseOfferingId(String courseOfferingId) {
		this.courseOfferingId = courseOfferingId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getClassSize() {
		return classSize;
	}
	public void setClassSize(String classSize) {
		this.classSize = classSize;
	}
	public String getWaitlistSize() {
		return waitlistSize;
	}
	public void setWaitlistSize(String waitlistSize) {
		this.waitlistSize = waitlistSize;
	}
	public String getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public List<FacultyBean> getFaculty() {
		return faculty;
	}
	public void setFaculty(List<FacultyBean> faculty) {
		this.faculty = faculty;
	}
	public List<ScheduleBean> getSchedule() {
		return schedule;
	}
	public void setSchedule(List<ScheduleBean> schedule) {
		this.schedule = schedule;
	}
	

}
