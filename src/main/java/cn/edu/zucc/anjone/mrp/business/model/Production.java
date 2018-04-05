package cn.edu.zucc.anjone.mrp.business.model;

import java.util.Date;

import cn.edu.zucc.anjone.mrp.util.PageRequest;

public class Production extends PageRequest{
	private int id;
	
	private String number;
	
	private String productNumber;
	
	private int amount;
	//0待生产 1 生产完成 2已撤销
	private String state;
	
	private Date date;
	
	private Date finishTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	
	
}
