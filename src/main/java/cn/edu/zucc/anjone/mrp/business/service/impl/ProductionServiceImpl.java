package cn.edu.zucc.anjone.mrp.business.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zucc.anjone.mrp.business.dto.ProductionDto;
import cn.edu.zucc.anjone.mrp.business.mapper.ProductionMapper;
import cn.edu.zucc.anjone.mrp.business.service.IProductionService;
import cn.edu.zucc.anjone.mrp.info.mapper.MaterialMapper;
import cn.edu.zucc.anjone.mrp.info.mapper.ProductDetailMapper;
import cn.edu.zucc.anjone.mrp.info.mapper.ProductMapper;
import cn.edu.zucc.anjone.mrp.info.model.Material;
import cn.edu.zucc.anjone.mrp.info.model.Product;
import cn.edu.zucc.anjone.mrp.info.model.ProductDetail;
import cn.edu.zucc.anjone.mrp.manage.dto.InventoryLogDto;
import cn.edu.zucc.anjone.mrp.manage.dto.ProductInventoryDto;
import cn.edu.zucc.anjone.mrp.manage.mapper.InventoryLogMapper;
import cn.edu.zucc.anjone.mrp.manage.mapper.MaterialInventoryMapper;
import cn.edu.zucc.anjone.mrp.manage.mapper.ProductInventoryMapper;
import cn.edu.zucc.anjone.mrp.manage.model.InventoryLog;
import cn.edu.zucc.anjone.mrp.manage.model.MaterialInventory;
import cn.edu.zucc.anjone.mrp.manage.service.impl.ProductInventoryServiceImpl;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Service
public class ProductionServiceImpl implements IProductionService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductInventoryServiceImpl.class);

    @Autowired
    private ProductionMapper productionMapper;
    
    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private ProductDetailMapper productdetailMapper;
    
    @Autowired
    private ProductInventoryMapper productInventoryMapper;
    
    @Autowired
    private MaterialMapper materialMapper;
    
    @Autowired
    private MaterialInventoryMapper materialInventoryMapper;
    
    @Autowired
    private InventoryLogMapper logisticsMapper;
    
    @Override
	public PageResponse queryPage(ProductionDto dto) {
		int a = 1;
		try {
		    a = Integer.parseInt(dto.getsId());
			dto.setId(a);
		} catch (NumberFormatException e) {}
		return new PageResponse( productionMapper.queryPage(dto) , productionMapper.getCount(dto) ,dto.getDraw());
	}
	
	@Override
	public AjaxResult save(ProductionDto dto) {
		if(productMapper.selectByNumber(dto.getProductNumber())==null)
			return new AjaxResult("0,,产品编号有误");
		dto.setDate(new Date());
		productionMapper.insert(dto);
		return new AjaxResult("1,,新增成功");
	}

	@Override
	@Transactional
	public AjaxResult updateState(ProductionDto dto) {
		productionMapper.updateState(dto);
		//生产完成
		if(dto.getState().equals("1")){
			InventoryLog log = null;
			//获取生产计划
			dto = productionMapper.selectByKey(dto.getId());
			//更新产品 库存 日志几率
			ProductInventoryDto pInventory = productInventoryMapper.selectByProductNumber(dto.getProductNumber());
			pInventory.setAmount( pInventory.getAmount()+dto.getAmount());
			productInventoryMapper.updateAmountByProductNumber(pInventory);

			Product product = productMapper.selectByKey(pInventory.getProductId() );
			log = new InventoryLogDto("4", product.getName(),product.getId() , dto.getAmount());
			logisticsMapper.insert(log);
			
			//更新原材料库存 日志记录
			MaterialInventory MI = null;
			log.setType("0");
			Material material = null;
			List<ProductDetail> list = productdetailMapper.selectByProductId(product.getId());
			for(ProductDetail detail : list){
				material = materialMapper.selectByKey(detail.getId());
				logisticsMapper.insert( new InventoryLogDto("1", material.getName(),material.getId() , dto.getAmount()) ); 
			}
		}
		return new AjaxResult("success","操作成功");
	}

}
