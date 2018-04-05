package cn.edu.zucc.anjone.mrp.info.service;

import cn.edu.zucc.anjone.mrp.info.dto.ProductTypeDto;
import cn.edu.zucc.anjone.mrp.info.model.ProductType;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

public interface IProductTypeService{
	/*
	 * save
	 * @return 0|1,message
	 */
	String save(ProductTypeDto dto);
	
	/*
	 * delete
	 * @return 0|1,message
	 */
	String deleteByKey(String id);
	
	/*
	 * query page
	 * @return PageResponse
	 */
	PageResponse queryPage(ProductTypeDto dto);
	
	/*
	 * query by id
	 * @return PageResponse
	 */
    ProductType selectByKey(String id);
}