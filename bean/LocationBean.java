package com.ncsu.cms.bean;
import java.io.Serializable;
public class LocationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roomNo;
	private String building;
	public LocationBean(String roomNo, String building) {
		super();
		this.roomNo = roomNo;
		this.building = building;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	
	
	
	

}

