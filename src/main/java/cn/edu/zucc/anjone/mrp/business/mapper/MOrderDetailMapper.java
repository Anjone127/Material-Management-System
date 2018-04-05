package cn.edu.zucc.anjone.mrp.business.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.business.dto.MOrderDetailDto;
import cn.edu.zucc.anjone.mrp.business.dto.MOrderDto;
import cn.edu.zucc.anjone.mrp.business.model.MOrderDetail;

public interface MOrderDetailMapper {
	
	List<MOrderDetail> queryPage(MOrderDetailDto dto);
	
	List<MOrderDetail> selectByOrderId(String orderId);
	
    int deleteByKey(String id);

    int insert(MOrderDetail detail);
    
    int updateAmountByKey(MOrderDetail detail);
    
    MOrderDetail selectByMaterialNumberAndOrderId(MOrderDetail detail);

	int getCount(MOrderDetailDto dto);

	MOrderDetail selectByKey(String id);

}
