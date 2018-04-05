package cn.edu.zucc.anjone.mrp.system.service;

import java.util.List;

import cn.edu.zucc.anjone.mrp.system.dto.RoleDto;
import cn.edu.zucc.anjone.mrp.system.model.Role;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageRequest;

public interface IRoleService 	{
	
	PageRequest querypage(RoleDto dto);
	
	List<Role> selectAll();
	
	AjaxResult insert(RoleDto dto);
	
	AjaxResult deleteByKey(String id);
}
