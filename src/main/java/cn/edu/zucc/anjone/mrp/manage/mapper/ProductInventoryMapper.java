package cn.edu.zucc.anjone.mrp.manage.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.manage.dto.ProductInventoryDto;
import cn.edu.zucc.anjone.mrp.manage.model.ProductInventory;

public interface ProductInventoryMapper {
	int insert(ProductInventoryDto dto);
	
    ProductInventoryDto selectByKey(String id);
    
    List<ProductInventoryDto> queryPage(ProductInventoryDto dto);
    
    int updateAmountByKey(ProductInventoryDto dto);
    
    int deleteByProductId(String id);
    
    int getCount(ProductInventoryDto dto);

    ProductInventory selectByProductId(String productId);

	int updateAmountByProductId(ProductInventory PI);

	int updateAmountByProductNumber(ProductInventoryDto pdto);

	ProductInventoryDto selectByProductNumber(String productNumber);
}