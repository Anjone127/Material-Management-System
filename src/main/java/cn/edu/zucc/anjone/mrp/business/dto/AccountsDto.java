package cn.edu.zucc.anjone.mrp.business.dto;

import java.util.Date;

import cn.edu.zucc.anjone.mrp.business.model.Accounts;

public class AccountsDto extends Accounts {

	private String stDate;

	private String endDate;

	private String peopleName ;

	private String peopleNumber ;
	
	private String OrderNumber;
	
	public String getStDate() {
		return stDate;
	}

	public void setStDate(String stDate) {
		this.stDate = stDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	public String getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(String peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	public String getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		OrderNumber = orderNumber;
	}

	
}