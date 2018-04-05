package cn.edu.zucc.anjone.mrp.manage.dto;

import cn.edu.zucc.anjone.mrp.manage.model.MaterialInventory;

public class MaterialInventoryDto extends MaterialInventory {
	// Material name
	private String name;
	// Material number
	private String number;
	
	private int preAmount;
	
	public MaterialInventoryDto(){}
	public MaterialInventoryDto(String materialId,String materialNumber){
		super(materialId,materialNumber);
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