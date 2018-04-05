package cn.edu.zucc.anjone.mrp.manage.dto;

import cn.edu.zucc.anjone.mrp.manage.model.ProductInventory;

public class ProductInventoryDto extends ProductInventory {
	// Product name
	String name;
	// Product number
	String number;
	
	private int preAmount;
	public ProductInventoryDto(){}
	
	public ProductInventoryDto(String productId){
		super(productId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getPreAmount() {
		return preAmount;
	}

	public void setPreAmount(int preAmount) {
		this.preAmount = preAmount;
	}
}