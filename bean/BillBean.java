package com.ncsu.cms.bean;
import java.io.Serializable;
public class BillBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String billAmount;
	
	public BillBean(String billAmount) {
		super();
		this.billAmount = billAmount;
	}
	public String getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(String billAmount) {
		this.billAmount = billAmount;
	}
	
	

}
