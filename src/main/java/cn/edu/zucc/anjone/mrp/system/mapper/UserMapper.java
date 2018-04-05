package cn.edu.zucc.anjone.mrp.system.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.system.dto.UserDto;
import cn.edu.zucc.anjone.mrp.system.model.User;


public interface UserMapper {
	public List<User> queryPage(UserDto dto);
	public int getCount(UserDto dto);
	public int insert(UserDto dto);
	public User selectByKey(String id);
	public int deleteByKey(String id);
	public int updateById(UserDto dto);
	public User selectByUserId(String userid);
	public String selectRoleNameByUserId(String userId);
	public int updateNameAndRoleById(UserDto dto);
}
