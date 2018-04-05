package cn.edu.zucc.anjone.mrp.manage.dto;

import cn.edu.zucc.anjone.mrp.manage.model.InventoryLog;

public class InventoryLogDto extends InventoryLog{
	
	public InventoryLogDto(){
		super();
	}
	
	public InventoryLogDto( String type, String inventoryName, String inventoryId, int amount) {
		super(type, inventoryName, inventoryId, amount);
	}
	
}