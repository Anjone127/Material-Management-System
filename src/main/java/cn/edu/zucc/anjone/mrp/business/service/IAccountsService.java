package cn.edu.zucc.anjone.mrp.business.service;

import java.util.Map;

import cn.edu.zucc.anjone.mrp.business.dto.AccountsDto;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

public interface IAccountsService{

	PageResponse queryPage(AccountsDto dto);

	AjaxResult updateState(AccountsDto dto);

	Map<String,Object> excelModel(AccountsDto dto);
}