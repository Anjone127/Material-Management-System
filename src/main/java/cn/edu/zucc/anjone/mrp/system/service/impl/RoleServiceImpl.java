package cn.edu.zucc.anjone.mrp.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zucc.anjone.mrp.system.dto.RoleDto;
import cn.edu.zucc.anjone.mrp.system.mapper.RoleMapper;
import cn.edu.zucc.anjone.mrp.system.model.Role;
import cn.edu.zucc.anjone.mrp.system.service.IRoleService;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageRequest;

@Service
public class RoleServiceImpl  implements IRoleService {
    @Autowired
    private RoleMapper RoleMapper;

	@Override
	public PageRequest querypage(RoleDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> selectAll() {
		return RoleMapper.selectAll();
	}

	@Override
	public AjaxResult insert(RoleDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AjaxResult deleteByKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
