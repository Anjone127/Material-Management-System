package cn.edu.zucc.anjone.mrp.info.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.info.dto.CustomerDto;
import cn.edu.zucc.anjone.mrp.info.model.Customer;

public interface CustomerMapper {
	
    int insert(CustomerDto dto);
    
    Customer selectByKey(String id);
    
    Customer selectByNumber(String id);
    
    List<Customer> queryPage(CustomerDto dto);
    
    int updateById(CustomerDto dto);
    
    int deleteByKey(String id);
    
    int getCount(CustomerDto dto);
    
}