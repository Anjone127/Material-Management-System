package cn.edu.zucc.anjone.mrp.manage.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.manage.dto.MaterialInventoryDto;
import cn.edu.zucc.anjone.mrp.manage.model.MaterialInventory;


public interface MaterialInventoryMapper {
	int insert(MaterialInventoryDto dto);
	
    MaterialInventoryDto selectByKey(String id);
    
    List<MaterialInventoryDto> queryPage(MaterialInventoryDto dto);
    
    int updateAmountByKey(MaterialInventoryDto dto);
    
    int deleteByMaterialId(String number);
    
    int getCount(MaterialInventoryDto dto);

	int updateAmountByMaterialId(MaterialInventory MI);

	MaterialInventory selectByMaterialId(String materialId);
}