package cn.edu.zucc.anjone.mrp.business.model;

import java.util.Date;

import cn.edu.zucc.anjone.mrp.util.PageRequest;

public class POrder extends PageRequest{
	private String id;

	private String number;

	private String customerNumber;

	private Double totalMoney;

	private Double cancelMoney;
	//0 未审核 1 未发货 2 发货中 3 订单完成 4已撤销 
	private String state;

	private Date startTime;

	private Date finalTime;

	private String address;

	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Double getCancelMoney() {
		return cancelMoney;
	}

	public void setCancelMoney(Double cancelMoney) {
		this.cancelMoney = cancelMoney;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(Date finalTime) {
		this.finalTime = finalTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}