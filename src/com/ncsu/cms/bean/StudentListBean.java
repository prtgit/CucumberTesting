package com.ncsu.cms.bean;
import java.io.Serializable;
public class StudentListBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private String deptId;
	private String gpa;
	private String email;
	private String residencyType;
	private String address;
	private String levelClassification;
	private String dob;
	private String billAmount;
	
	public StudentListBean(String userId, String userName, String firstName, String lastName, String email, String address, String phoneNumber, 
			String deptId, String gpa, String residencyType, String levelClassification) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.deptId = deptId;
		this.gpa = gpa;
		this.email = email;
		this.residencyType = residencyType;
		this.address = address;
		this.levelClassification = levelClassification;
	}
	
	public StudentListBean(String userId, String userName, String firstName, String lastName, String email, String address, String phoneNumber, 
			String deptId, String gpa, String residencyType, String levelClassification, String dob, String billAmount) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.deptId = deptId;
		this.gpa = gpa;
		this.email = email;
		this.residencyType = residencyType;
		this.address = address;
		this.levelClassification = levelClassification;
		this.dob = dob;
		this.billAmount = billAmount;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getGpa() {
		return gpa;
	}
	public void setGpa(String gpa) {
		this.gpa = gpa;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getResidencyType() {
		return residencyType;
	}
	public void setResidencyType(String residencyType) {
		this.residencyType = residencyType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLevelClassification() {
		return levelClassification;
	}
	public void setLevelClassification(String levelClassification) {
		this.levelClassification = levelClassification;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(String billAmount) {
		this.billAmount = billAmount;
	}
	
	

}
