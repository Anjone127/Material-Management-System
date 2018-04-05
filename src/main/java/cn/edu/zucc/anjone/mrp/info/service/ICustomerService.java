package cn.edu.zucc.anjone.mrp.info.service;

import cn.edu.zucc.anjone.mrp.info.dto.CustomerDto;
import cn.edu.zucc.anjone.mrp.info.model.Customer;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

public interface ICustomerService  {
	/*
	 * save
	 * @return 0|1,message
	 */
	String save(CustomerDto dto);
	
	/*
	 * delete
	 * @return 0|1,message
	 */
	String deleteByKey(String id);
	
	/*
	 * query page
	 * @return PageResponse
	 */
	PageResponse queryPage(CustomerDto dto);
	
	/*
	 * query by id
	 * @return PageResponse
	 */
    Customer selectByKey(String id);
}