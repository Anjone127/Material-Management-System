package cn.edu.zucc.anjone.mrp.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.anjone.mrp.info.dto.ProductDetailDto;
import cn.edu.zucc.anjone.mrp.info.dto.ProductDto;
import cn.edu.zucc.anjone.mrp.info.mapper.MaterialMapper;
import cn.edu.zucc.anjone.mrp.info.mapper.ProductDetailMapper;
import cn.edu.zucc.anjone.mrp.info.mapper.ProductMapper;
import cn.edu.zucc.anjone.mrp.info.model.Material;
import cn.edu.zucc.anjone.mrp.info.model.Product;
import cn.edu.zucc.anjone.mrp.info.model.ProductDetail;
import cn.edu.zucc.anjone.mrp.info.service.IProductService;
import cn.edu.zucc.anjone.mrp.manage.dto.ProductInventoryDto;
import cn.edu.zucc.anjone.mrp.manage.mapper.ProductInventoryMapper;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Service
public class ProductServiceImpl  implements IProductService {
	
    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private ProductInventoryMapper productInventoryMapper;
    
    @Autowired
    private ProductDetailMapper productDetailMapper;
    
    @Autowired
    private MaterialMapper materialMapper;

	@Override
	public AjaxResult save(ProductDto dto) {
		if(dto.getId()==""||dto.getId()==null){
			if(null != productMapper.selectByNumber(dto.getNumber()))
				return new AjaxResult("0,,新增失败,编号已存在");
			productMapper.insert(dto);
			//产品库存记录
			ProductInventoryDto pidto = new ProductInventoryDto(dto.getId()); 
			if(1==productInventoryMapper.insert(pidto))
				return new AjaxResult(dto.getId(),"新增成功");
			else return new AjaxResult("0,,新增失败");
		}else{
			if(1==productMapper.updateById(dto))
				return new AjaxResult("1,,修改成功");
			else return new AjaxResult("0,,修改失败");
		}
	}

	@Override
	@Transactional
	public String deleteByKey(String id) {
		if(productMapper.deleteByKey(id)==productInventoryMapper.deleteByProductId(id))
			return "1,,删除成功";
		else
			return "0,,删除失败";
	}

	@Override
	public PageResponse queryPage(ProductDto dto) {
		if(dto.getName() !=null &&dto.getName().length()>0)
			dto.setName('%' +dto.getName()+'%');
		if(dto.getName() !=null &&dto.getNumber().length()>0)
			dto.setNumber('%' +dto.getNumber()+'%');
		return new PageResponse( productMapper.queryPage(dto) , productMapper.getCount(dto) ,dto.getDraw());
	}

	@Override
	public Product selectByKey(String id) {
		return productMapper.selectByKey(id);
	}

	@Override
	public PageResponse queryPageDetiaList(ProductDetailDto dto) {
		List<ProductDetailDto> l = productDetailMapper.queryPage(dto);
		int a = productDetailMapper.getCount(dto);
		return new PageResponse( productDetailMapper.queryPage(dto) , productDetailMapper.getCount(dto) ,dto.getDraw());
	}

	//保存详情
	@Override
	@Transactional
	public AjaxResult saveDetail(ProductDetailDto dto) {
		Product product = null;
		ProductDetail pre = null;
		Material material = null;
		if(dto.getId() != null){ //存在详情
			pre = productDetailMapper.selectByKey(dto.getId());
			product = productMapper.selectByKey(pre.getProductId());
			material = materialMapper.selectByKey(pre.getMaterialId());
			if(dto.getAmount()==0)
				productDetailMapper.deleteByKey(dto);
			else
				productDetailMapper.updateAmountByKey(dto);
			product.setCost( product.getCost() + ( dto.getAmount() - pre.getAmount())*material.getPrice());
		}else{//根据Mid Pid添加
			pre = productDetailMapper.selectByPIdMId(dto);
			product = productMapper.selectByKey(dto.getProductId());
			material = materialMapper.selectByKey(dto.getMaterialId());
			if(pre==null){
				productDetailMapper.insert(dto);
				product.setCost( product.getCost() +  dto.getAmount()*material.getPrice());
			}
			else{
				dto.setAmount( dto.getAmount() + pre.getAmount());
				dto.setId(pre.getId());
				productDetailMapper.updateAmountByKey(dto);
				product.setCost( product.getCost() + ( dto.getAmount() - pre.getAmount())*material.getPrice());
			}
			productMapper.updateCostById(product);
		}
		productMapper.updateCostById(product);
		return new AjaxResult("1,,操作成功");
	}
}