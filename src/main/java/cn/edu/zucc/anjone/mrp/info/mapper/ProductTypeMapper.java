package cn.edu.zucc.anjone.mrp.info.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.info.dto.ProductTypeDto;
import cn.edu.zucc.anjone.mrp.info.model.ProductType;

public interface ProductTypeMapper {
    int insert(ProductTypeDto dto);
    
    ProductType selectByKey(String id);
    
    ProductType selectByNumber(String number);
    
    List<ProductType> selectAll();
    
    List<ProductType> queryPage(ProductTypeDto dto);
    
    int updateById(ProductTypeDto dto);
    
    int deleteByKey(String id);
    
    int getCount(ProductTypeDto dto);
    
    
}