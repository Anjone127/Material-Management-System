package cn.edu.zucc.anjone.mrp.info.service;

import cn.edu.zucc.anjone.mrp.info.dto.MaterialDto;
import cn.edu.zucc.anjone.mrp.info.model.Material;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

public interface IMaterialService{
	/*
	 * save
	 * @return 0|1,message
	 */
	String save(MaterialDto dto);
	
	/*
	 * delete
	 * @return 0|1,message
	 */
	String deleteByKey(String id);
	
	/*
	 * query page
	 * @return PageResponse
	 */
	PageResponse queryPage(MaterialDto dto);
	
	/*
	 * query by id
	 * @return PageResponse
	 */
    Material selectByKey(String id);
}