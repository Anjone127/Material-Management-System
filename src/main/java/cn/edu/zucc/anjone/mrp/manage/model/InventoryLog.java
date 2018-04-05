package cn.edu.zucc.anjone.mrp.manage.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.edu.zucc.anjone.mrp.manage.dto.MaterialInventoryDto;
import cn.edu.zucc.anjone.mrp.manage.dto.ProductInventoryDto;
import cn.edu.zucc.anjone.mrp.util.PageRequest;

public class InventoryLog extends PageRequest{
	
	private String id;
	// 0原材料入库   1生产消耗 2库存盘点  3产品入库  4产品销售 
	private String type;
	
	private Date date;

	private String inventoryName;
	
	private String inventoryId;
	
	private int amount;
	
	public InventoryLog(){}
	
	public InventoryLog(String type ,String inventoryName ,String inventoryId,int amount){
		this.date = new Date();
		this.type = type;
		this.inventoryName = inventoryName;
		this.inventoryId = inventoryId;
		this.amount = amount;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public String getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}