package cn.edu.zucc.anjone.mrp.business.service;

import cn.edu.zucc.anjone.mrp.business.dto.MOrderDetailDto;
import cn.edu.zucc.anjone.mrp.business.dto.MOrderDto;
import cn.edu.zucc.anjone.mrp.business.model.MOrder;
import cn.edu.zucc.anjone.mrp.business.model.MOrderDetail;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

public interface IMOrderService {
	/*
	 * query page
	 * @return PageResponse
	 */
	PageResponse queryPage(MOrderDto dto);
	
	/*
	 * query by id
	 * @return PageResponse
	 */
	MOrder selectByKey(String id);
	
	/*
	 * save 
	 * @return AjaxResult
	 */
	AjaxResult save(MOrderDto dto);
	
	/*
	 * save M Order detail
	 * @return AjaxResult
	 */
	AjaxResult saveDetial(MOrderDetail detail);
	
	/*
	 * query page
	 * @return PageResponse
	 */
	PageResponse queryPageDetailList(MOrderDetailDto dto);
	
	/*
	 * delete M order detail
	 * @return AjaxResult
	 */
	AjaxResult deleteDetailById(String id);

	/*
	 * query update order state
	 * @return AjaxResult
	 */
	AjaxResult updateState(MOrderDto dto);
}
