package cn.edu.zucc.anjone.mrp.system.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zucc.anjone.mrp.system.dto.UserDto;
import cn.edu.zucc.anjone.mrp.system.mapper.UserMapper;
import cn.edu.zucc.anjone.mrp.system.model.User;
import cn.edu.zucc.anjone.mrp.system.service.IUserService;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Service
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired	
    private UserMapper userMapper;

	@Override
	public PageResponse queryPage(UserDto dto) {
		if(dto.getUserId().length()>0)
			dto.setUserId('%' +dto.getUserId()+'%');
		if(dto.getUserName().length()>0)
			dto.setUserName('%' +dto.getUserName()+'%');
		List<User> list = userMapper.queryPage(dto);
		PageResponse page = new PageResponse(list , userMapper.getCount(dto) , dto.getDraw());
		return page;
	}

	@Override
	public int insert(UserDto dto) {
		return userMapper.insert(dto);
	}

	@Override
	public int deleteByKey(String id) {
		return userMapper.deleteByKey(id);
	}

	@Override
	public int save(UserDto dto) {
		dto.setUserPasswordSalt("1231");
		if(dto.getId()==null || dto.getId()==""){
			return userMapper.insert(dto);
		}else if(dto.getUserPassword().length()>5){
			return 1+userMapper.updateById(dto);
		}else{
			return 1+userMapper.updateNameAndRoleById(dto);
		}
	}

	@Override
	public User selectByKey(String id) {
		return userMapper.selectByKey(id);
	}
}