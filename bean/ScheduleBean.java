package com.ncsu.cms.bean;
import java.io.Serializable;
public class ScheduleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fromTime;
	private String toTime;
	private String day;
	
	public ScheduleBean(String fromTime, String toTime, String day) {
		super();
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.day = day;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	

}
