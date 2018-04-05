package cn.edu.zucc.anjone.mrp.system.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.system.dto.RoleDto;
import cn.edu.zucc.anjone.mrp.system.model.Role;

public interface RoleMapper {
	List<Role> queryPage(RoleDto dto);
	Role selectByKey(String id);
	int updateById(RoleDto dto);
	int insert(RoleDto dto);
	int deleteByKey(String id);
	int getCount(RoleDto dto);
	List<Role> selectAll();
}