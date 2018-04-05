package cn.edu.zucc.anjone.mrp.info.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zucc.anjone.mrp.info.dto.ProductTypeDto;
import cn.edu.zucc.anjone.mrp.info.mapper.ProductTypeMapper;
import cn.edu.zucc.anjone.mrp.info.model.ProductType;
import cn.edu.zucc.anjone.mrp.info.service.IProductTypeService;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Service
public class ProductTypeServiceImpl  implements IProductTypeService {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(ProductTypeServiceImpl.class);

    @Autowired
    private ProductTypeMapper productTypeMapper;

	@Override
	public String save(ProductTypeDto dto) {
		if(dto.getId()==""||dto.getId()==null){
			ProductType type = productTypeMapper.selectByNumber(dto.getNumber());
			if(type!=null)
				return "0,,新增失败,编号已存在";
			if(1==productTypeMapper.insert(dto))
				return "1,,新增成功";
			else return "0,,新增失败";
		}else{
			if(1==productTypeMapper.updateById(dto))
				return "1,,修改成功";
			else return "0,,修改失败";
		}
	}

	@Override
	public String deleteByKey(String id) {
		if(productTypeMapper.deleteByKey(id)==1)
			return "1,,删除成功";
		else
			return "0,,删除失败";
	}

	@Override
	public PageResponse queryPage(ProductTypeDto dto) {
		if(dto.getName() !=null &&dto.getName().length()>0)
			dto.setName('%' +dto.getName()+'%');
		if(dto.getName() !=null &&dto.getNumber().length()>0)
			dto.setNumber('%' +dto.getNumber()+'%');
		return new PageResponse( productTypeMapper.queryPage(dto) , productTypeMapper.getCount(dto) ,dto.getDraw());
	}

	@Override
	public ProductType selectByKey(String id) {
		return productTypeMapper.selectByKey(id);
	}
}