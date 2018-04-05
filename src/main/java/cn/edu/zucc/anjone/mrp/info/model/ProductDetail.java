package cn.edu.zucc.anjone.mrp.info.model;

import cn.edu.zucc.anjone.mrp.util.PageRequest;

public class ProductDetail extends PageRequest{
	
	private String id;
	
	private String productId;
	
	private String materialId;
	
	private int amount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
