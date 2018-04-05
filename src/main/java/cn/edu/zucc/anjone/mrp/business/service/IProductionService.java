package cn.edu.zucc.anjone.mrp.business.service;

import cn.edu.zucc.anjone.mrp.business.dto.ProductionDto;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

public interface IProductionService {
	
	/*
	 * query page
	 * @return PageResponse
	 */
	PageResponse queryPage(ProductionDto dto);
	
	/*
	 * save
	 * @return AjaxResult
	 */
	AjaxResult save(ProductionDto dto);

	AjaxResult updateState(ProductionDto dto);

}
