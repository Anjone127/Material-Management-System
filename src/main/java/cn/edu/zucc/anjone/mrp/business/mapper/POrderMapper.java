package cn.edu.zucc.anjone.mrp.business.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.business.dto.POrderDto;
import cn.edu.zucc.anjone.mrp.business.model.POrder;

public interface POrderMapper {

    int insert(POrderDto dto);
    
    POrderDto selectByKey(String id);
    
    POrder selectByNumber(String number);
    
    List<POrderDto> queryPage(POrderDto dto);
    
    int updateById(POrderDto dto);
    
    int getCount(POrderDto dto);

	int updateState(POrderDto dto);

	int updateTotalMoney(POrderDto order);
}