package cn.edu.zucc.anjone.mrp.business.model;

import cn.edu.zucc.anjone.mrp.util.PageRequest;

public class MOrderDetail extends PageRequest {
	private String id;

    private String orderId;

    private String materialId;
    
    private String materialNumber;

    private int amount;

    private int cancelAmount;
    
    private int price;
    
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	
	public String getMaterialNumber() {
		return materialNumber;
	}

	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCancelAmount() {
		return cancelAmount;
	}

	public void setCancelAmount(int cancelAmount) {
		this.cancelAmount = cancelAmount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
    
    
}
