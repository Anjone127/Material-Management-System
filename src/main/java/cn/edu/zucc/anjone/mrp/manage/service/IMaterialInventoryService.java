package cn.edu.zucc.anjone.mrp.manage.service;

import cn.edu.zucc.anjone.mrp.manage.dto.MaterialInventoryDto;
import cn.edu.zucc.anjone.mrp.manage.model.MaterialInventory;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

public interface IMaterialInventoryService {
	/*
	 * query page
	 * @return PageResponse
	 */
	PageResponse queryPage(MaterialInventoryDto dto);
    
    /*
	 * query by id
	 * @return  0|1,,message 
	 */
	String checkAmount(MaterialInventoryDto dto);

	MaterialInventoryDto selectByKey(String id);
}