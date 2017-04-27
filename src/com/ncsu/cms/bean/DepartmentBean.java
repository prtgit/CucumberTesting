package com.ncsu.cms.bean;
import java.io.Serializable;
public class DepartmentBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String departmentId;
	private String departmentName;
	
	public DepartmentBean(String departmentId, String departmentName) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}
	
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
