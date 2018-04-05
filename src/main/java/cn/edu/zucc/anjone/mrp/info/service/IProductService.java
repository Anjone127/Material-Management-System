package cn.edu.zucc.anjone.mrp.info.service;

import cn.edu.zucc.anjone.mrp.info.dto.ProductDetailDto;
import cn.edu.zucc.anjone.mrp.info.dto.ProductDto;
import cn.edu.zucc.anjone.mrp.info.model.Product;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

public interface IProductService  {
	/*
	 * save
	 * @return 0|1,message
	 */
	AjaxResult save(ProductDto dto);
	
	/*
	 * delete
	 * @return 0|1,message
	 */
	String deleteByKey(String id);
	
	/*
	 * query page
	 * @return PageResponse
	 */
	PageResponse queryPage(ProductDto dto);
	
	/*
	 * query by id
	 * @return PageResponse
	 */
    Product selectByKey(String id);
    
    /*
	 * query page by id(product detail)
	 * @return PageResponse
	 */
	PageResponse queryPageDetiaList(ProductDetailDto dto);
	
	AjaxResult saveDetail(ProductDetailDto dto);
}