package com.ncsu.cms.bean;

import java.io.Serializable;

public class StudentBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String studentId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	private String email;
	private String gpa;
	private String departmentName;
	private String levelClassification;
	private String residencyClassification;
	
	public StudentBean(String studentId, String firstName, String lastName, String phoneNumber, String address,
			String email, String gpa, String departmentName, String levelClassification,
			String residencyClassification) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
		this.gpa = gpa;
		this.departmentName = departmentName;
		this.levelClassification = levelClassification;
		this.residencyClassification = residencyClassification;
	}
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGpa() {
		return gpa;
	}
	public void setGpa(String gpa) {
		this.gpa = gpa;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getLevelClassification() {
		return levelClassification;
	}
	public void setLevelClassification(String levelClassification) {
		this.levelClassification = levelClassification;
	}
	public String getResidencyClassification() {
		return residencyClassification;
	}
	public void setResidencyClassification(String residencyClassification) {
		this.residencyClassification = residencyClassification;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
