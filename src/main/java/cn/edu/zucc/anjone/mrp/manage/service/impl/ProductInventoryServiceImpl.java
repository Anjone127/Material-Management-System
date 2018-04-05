package cn.edu.zucc.anjone.mrp.manage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.anjone.mrp.info.mapper.ProductMapper;
import cn.edu.zucc.anjone.mrp.info.model.Product;
import cn.edu.zucc.anjone.mrp.manage.dto.InventoryLogDto;
import cn.edu.zucc.anjone.mrp.manage.dto.ProductInventoryDto;
import cn.edu.zucc.anjone.mrp.manage.mapper.InventoryLogMapper;
import cn.edu.zucc.anjone.mrp.manage.mapper.ProductInventoryMapper;
import cn.edu.zucc.anjone.mrp.manage.model.ProductInventory;
import cn.edu.zucc.anjone.mrp.manage.service.IProductInventoryService;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Service
public class ProductInventoryServiceImpl implements IProductInventoryService {

    private static final Logger logger = LoggerFactory.getLogger(ProductInventoryServiceImpl.class);

    @Autowired
    private ProductInventoryMapper productInventoryMapper;
    
    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private InventoryLogMapper logisticsMapper;

	@Override
	public PageResponse queryPage(ProductInventoryDto dto) {
		if(dto.getName() !=null &&dto.getName().length()>0)
			dto.setName('%' +dto.getName()+'%');
		if(dto.getNumber() !=null &&dto.getNumber().length()>0)
			dto.setNumber('%' +dto.getNumber()+'%');
		return new PageResponse( productInventoryMapper.queryPage(dto) , productInventoryMapper.getCount(dto) ,dto.getDraw());
	}

	@Override
	public ProductInventory selectByKey(String id) {
		return productInventoryMapper.selectByKey(id);
	}
	

	@Override
	@Transactional
	public String checkAmount(ProductInventoryDto dto) {
		ProductInventoryDto pre =  productInventoryMapper.selectByKey(dto.getId());
		productInventoryMapper.updateAmountByKey(dto);
		Product product = productMapper.selectByKey(pre.getProductId());
		InventoryLogDto log = new InventoryLogDto("2", product.getName(),product.getId() , dto.getAmount() -pre.getAmount());
		logisticsMapper.insert(log);
		return "1,,操作成功";
	}
}