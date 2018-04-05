package cn.edu.zucc.anjone.mrp.business.service;

import cn.edu.zucc.anjone.mrp.business.dto.POrderDetailDto;
import cn.edu.zucc.anjone.mrp.business.dto.POrderDto;
import cn.edu.zucc.anjone.mrp.business.model.POrderDetail;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

public interface IPOrderService {
	/*
	 * query page
	 * @return PageResponse
	 */
	PageResponse queryPage(POrderDto dto);
	
	/*
	 * query by id
	 * @return POrderDto
	 */
	POrderDto selectOrderByKey(String id);
	
	/*
	 * save P order detail
	 * @return POrderDto
	 */
	AjaxResult saveOrder(POrderDto dto);
	
	/*
	 * query page
	 * @return POrderDto
	 */
	PageResponse queryPageDetailList(POrderDetailDto dto);

	/*
	 * add detail
	 * @return POrderDto
	 */
	AjaxResult saveDetial(POrderDetail detail);
	
	/*
	 * delete P order detail
	 * @return AjaxResult
	 */
	AjaxResult deleteDetailById(String id);
	
	/*
	 * update order state
	 * @return AjaxResult
	 */
	AjaxResult updateState(POrderDto dto);
}