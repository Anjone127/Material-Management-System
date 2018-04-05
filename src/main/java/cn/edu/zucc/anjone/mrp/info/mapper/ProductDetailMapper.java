package cn.edu.zucc.anjone.mrp.info.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.info.dto.ProductDetailDto;
import cn.edu.zucc.anjone.mrp.info.model.ProductDetail;

public interface ProductDetailMapper {
	
	int getCount(ProductDetailDto dto);

	List<ProductDetailDto> queryPage(ProductDetailDto dto);

	void insert(ProductDetailDto dto);

	void updateAmountByKey(ProductDetailDto dto);

	ProductDetail selectByPIdMId(ProductDetailDto dto);

	ProductDetail selectByKey(String id);

	void deleteByKey(ProductDetailDto dto);

	List<ProductDetail> selectByProductId(String id);
}
