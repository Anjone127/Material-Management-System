package cn.edu.zucc.anjone.mrp.business.model;

import cn.edu.zucc.anjone.mrp.util.PageRequest;

public class POrderDetail extends PageRequest{
	
    private String id;

    private String orderId;

    private String productId;
    
    private String productNumber;

    private int amount;
    
    private int cancelAmount;
    
    private double price;

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

	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public int getCancelAmount() {
		return cancelAmount;
	}

	public void setCancelAmount(int cancelAmount) {
		this.cancelAmount = cancelAmount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
    
    

}