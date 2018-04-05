package cn.edu.zucc.anjone.mrp.info.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.info.dto.ProductDetailDto;
import cn.edu.zucc.anjone.mrp.info.dto.ProductDto;
import cn.edu.zucc.anjone.mrp.info.model.Product;

public interface ProductMapper {
    int insert(ProductDto dto);
    
    Product selectByKey(String id);
    
    Product selectByNumber(String id);
    
    List<Product> queryPage(ProductDto dto);
    
    int updateById(ProductDto dto);
    
    int deleteByKey(String id);
    
    int getCount(ProductDto dto);

	void updateCostById(Product product);
}