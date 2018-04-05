package cn.edu.zucc.anjone.mrp.business.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.business.dto.MOrderDto;
import cn.edu.zucc.anjone.mrp.business.model.MOrder;

public interface MOrderMapper {

    int insert(MOrderDto dto);
    
    MOrder selectByKey(String id);
    
    MOrder selectByNumber(String number);
    
    List<MOrderDto> queryPage(MOrderDto dto);
    
    int updateById(MOrderDto dto);
    
	int updateState(MOrderDto dto);
	
	int updateTotalMoney(MOrderDto dto);
	
	int updateCancelMoney(MOrderDto dto);
	
	int updateRemark(MOrderDto dto);
	
    int getCount(MOrderDto dto);
}
