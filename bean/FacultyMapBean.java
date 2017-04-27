package com.ncsu.cms.bean;
import java.io.Serializable;
public class FacultyMapBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String facultyId;
	private String facultyFullName;
	public String getFacultyFullName() {
		return facultyFullName;
	}
	public void setFacultyFullName(String facultyFullName) {
		this.facultyFullName = facultyFullName;
	}
	public String getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}
	public FacultyMapBean(String facultyId, String facultyFullName) {
		super();
		this.facultyId = facultyId;
		this.facultyFullName = facultyFullName;
	}
	

}
