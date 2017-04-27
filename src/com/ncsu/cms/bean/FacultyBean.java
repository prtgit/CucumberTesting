package com.ncsu.cms.bean;
import java.io.Serializable;
public class FacultyBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String facultyId;
	private String firstName;	
	private String lastName;
	
	public FacultyBean(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public FacultyBean(String facultyId, String firstName, String lastName) {
		super();
		this.facultyId = facultyId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}
	
	
	

}

