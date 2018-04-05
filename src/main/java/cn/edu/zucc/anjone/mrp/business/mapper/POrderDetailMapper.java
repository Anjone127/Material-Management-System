package cn.edu.zucc.anjone.mrp.business.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.business.dto.POrderDetailDto;
import cn.edu.zucc.anjone.mrp.business.model.POrderDetail;

public interface POrderDetailMapper {
    int deleteByKey(String id);

    int insert(POrderDetail record);

    int insertSelective(POrderDetail record);

    POrderDetailDto selectByKey(String id);

	int getCount(POrderDetailDto dto);

	List<POrderDetail> queryPage(POrderDetailDto dto);

	POrderDetail selectByProductNumberAndOrderId(POrderDetail detail);

	int updateAmountByKey(POrderDetail d);

	List<POrderDetail> selectByOrderId(String id);
}