package cn.edu.zucc.anjone.mrp.business.model;

import java.util.Date;

import cn.edu.zucc.anjone.mrp.util.PageRequest;

public class Accounts extends PageRequest{
    private String id;

    private String peopleId;

    private String orderId;

    private Double amount;
    //账目类型 0：应收   1 ：应付
    private String type;
    
    private Date date;
    //状态0：未完成 1：已完成
    private String state;

    private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    
}