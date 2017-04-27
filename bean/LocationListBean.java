package com.ncsu.cms.bean;
import java.io.Serializable;
public class LocationListBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String locationId;
	private String roomNo;
	private String building;
	public LocationListBean(String locationId, String roomNo, String building) {
		super();
		this.locationId = locationId;
		this.roomNo = roomNo;
		this.building = building;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
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
