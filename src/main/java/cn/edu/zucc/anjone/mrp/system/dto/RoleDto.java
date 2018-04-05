package cn.edu.zucc.anjone.mrp.system.dto;

import cn.edu.zucc.anjone.mrp.system.model.Role;

public class RoleDto extends Role {
	
	private String[] permisions;

    public String[] getPermisions() {
        return permisions;
    }

    public void setPermisions(String[] permisions) {
        this.permisions = permisions;
    }
}
