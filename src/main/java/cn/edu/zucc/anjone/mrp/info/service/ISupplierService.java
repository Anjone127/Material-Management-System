package cn.edu.zucc.anjone.mrp.info.service;

import cn.edu.zucc.anjone.mrp.info.dto.SupplierDto;
import cn.edu.zucc.anjone.mrp.info.model.Supplier;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

public interface ISupplierService  {
	/*
	 * save
	 * @return 0|1,message
	 */
	String save(SupplierDto dto);
	
	/*
	 * delete
	 * @return 0|1,message
	 */
	String deleteByKey(String id);
	
	/*
	 * query page
	 * @return PageResponse
	 */
	PageResponse queryPage(SupplierDto dto);
	
	/*
	 * query by id
	 * @return PageResponse
	 */
    Supplier selectByKey(String id);
    
    /*
	 * query by name | number
	 * @return 0|1,message
	 */
    AjaxResult selectByInfo(String info);
}