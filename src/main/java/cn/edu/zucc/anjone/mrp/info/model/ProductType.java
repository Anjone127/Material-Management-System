package cn.edu.zucc.anjone.mrp.info.model;

import cn.edu.zucc.anjone.mrp.util.PageRequest;

public class ProductType extends PageRequest{
    private String id;

    private String number;

    private String name;
    
    private String  description;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}