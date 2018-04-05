package cn.edu.zucc.anjone.mrp.business.dto;

import cn.edu.zucc.anjone.mrp.business.model.Production;

public class ProductionDto extends Production {
	private String sId;
	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}
	
}
