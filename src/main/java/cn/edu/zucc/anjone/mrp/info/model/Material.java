package cn.edu.zucc.anjone.mrp.info.model;

import cn.edu.zucc.anjone.mrp.util.PageRequest;

public class Material extends PageRequest{
    private String id;
    
    private String supplierNumber;
    
    private String number;

    private String name;

    private Double price;

    //原材料种类 0 处理器 1散热器 2 主板 3 显卡 4 内存 5 硬盘 6 机箱 7 电源
    private String type;
    
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    
    public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}