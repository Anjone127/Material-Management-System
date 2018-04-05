package cn.edu.zucc.anjone.mrp.manage.model;

import cn.edu.zucc.anjone.mrp.util.PageRequest;

public class ProductInventory extends PageRequest {
    private String id;

    private String productId;
    
    private String productNumber;
    
    private Integer amount;
    
    public ProductInventory(){}
    
    public ProductInventory( String materialId){
    	this.productId = materialId;
    	this.amount=0;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

	public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}