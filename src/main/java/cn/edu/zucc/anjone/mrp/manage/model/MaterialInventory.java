package cn.edu.zucc.anjone.mrp.manage.model;

import cn.edu.zucc.anjone.mrp.util.PageRequest;

public class MaterialInventory  extends PageRequest{
    private String id;

    private String materialId;

    private String materialNumber;
    
    private int amount;
    
    public MaterialInventory(){}
    
    public MaterialInventory(String materialId,String materialNumber){
    	this.id = null;
    	this.materialId = materialId;
    	this.materialNumber = materialNumber;
    	this.amount = 0;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId == null ? null : materialId.trim();
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
    
}