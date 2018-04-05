package cn.edu.zucc.anjone.mrp.business.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.business.dto.AccountsDto;
import cn.edu.zucc.anjone.mrp.business.model.Accounts;

public interface AccountsMapper {
	
    int insert(AccountsDto dto);

	int getCount(AccountsDto dto);

	List<AccountsDto> queryPage(AccountsDto dto);
	
	List<AccountsDto> selectByLimit(AccountsDto dto);

	int updateState(AccountsDto dto);
}