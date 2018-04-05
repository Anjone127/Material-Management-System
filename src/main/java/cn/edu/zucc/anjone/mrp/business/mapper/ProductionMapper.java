package cn.edu.zucc.anjone.mrp.business.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.business.dto.ProductionDto;
import cn.edu.zucc.anjone.mrp.business.model.Production;

public interface ProductionMapper {
	int insert(ProductionDto dto);
	
    ProductionDto selectByKey(int id);
    
    List<ProductionDto> queryPage(ProductionDto dto);
    
    int updateAmountByKey(ProductionDto dto);
    
    int deleteByProductId(String id);
    
    int getCount(ProductionDto dto);

	int updateState(ProductionDto dto);
}
