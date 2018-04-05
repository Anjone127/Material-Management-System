package cn.edu.zucc.anjone.mrp.system.dto;

import cn.edu.zucc.anjone.mrp.system.model.User;

public class UserDto extends User {
	
	private String remember;

	public String getRemember() {
		return remember;
	}

	public void setRemember(String remember) {
		this.remember = remember;
	}
	
	
}