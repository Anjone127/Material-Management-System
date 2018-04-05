package cn.edu.zucc.anjone.mrp.manage.service;

import cn.edu.zucc.anjone.mrp.manage.dto.ProductInventoryDto;
import cn.edu.zucc.anjone.mrp.manage.model.ProductInventory;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

public interface IProductInventoryService{
	/*
	 * query page
	 * @return PageResponse
	 */
	PageResponse queryPage(ProductInventoryDto dto);
	
	/*
	 * query by id
	 * @return PageResponse
	 */
    ProductInventory selectByKey(String id);
    /*
	 * query by id
	 * @return  0|1,,message 
	 */
	String checkAmount(ProductInventoryDto dto);
}