package cn.edu.zucc.anjone.mrp.system.service;

import cn.edu.zucc.anjone.mrp.system.dto.UserDto;
import cn.edu.zucc.anjone.mrp.system.model.User;
import cn.edu.zucc.anjone.mrp.util.PageResponse;


public interface IUserService {

	public PageResponse queryPage(UserDto dto);
	
	public int save(UserDto dto);
	
	public int deleteByKey(String id);

	public User selectByKey(String id);

	int insert(UserDto dto);
}