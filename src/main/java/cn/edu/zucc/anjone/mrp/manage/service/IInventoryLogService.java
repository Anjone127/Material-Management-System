package cn.edu.zucc.anjone.mrp.manage.service;

import cn.edu.zucc.anjone.mrp.manage.dto.InventoryLogDto;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

public interface IInventoryLogService{
	/*
	 * query page
	 * @return PageResponse
	 */
	PageResponse queryPage(InventoryLogDto dto);
}