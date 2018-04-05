package cn.edu.zucc.anjone.mrp.info.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.anjone.mrp.info.dto.MaterialDto;
import cn.edu.zucc.anjone.mrp.info.mapper.MaterialMapper;
import cn.edu.zucc.anjone.mrp.info.mapper.SupplierMapper;
import cn.edu.zucc.anjone.mrp.info.model.Material;
import cn.edu.zucc.anjone.mrp.info.model.Supplier;
import cn.edu.zucc.anjone.mrp.info.service.IMaterialService;
import cn.edu.zucc.anjone.mrp.manage.dto.MaterialInventoryDto;
import cn.edu.zucc.anjone.mrp.manage.mapper.MaterialInventoryMapper;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Service
public class MaterialServiceImpl  implements IMaterialService {

    private static final Logger logger = LoggerFactory.getLogger(MaterialServiceImpl.class);

    @Autowired
    private MaterialMapper materialMapper;
    
    @Autowired
    private MaterialInventoryMapper materialInventoryMapper;
    
    @Autowired
    private SupplierMapper supplierMapper;
    
    @Transactional
	public String save(MaterialDto dto) {
		if(dto.getId()==""||dto.getId()==null){
			if(null != materialMapper.selectByNumber(dto.getNumber()))
				return "0,,新增失败,编号已存在";
			Supplier s = supplierMapper.selectByNumber(dto.getSupplierNumber());
			if(null == s)
				return "0,,新增失败,供应商编号错误,供应商不存在";
			materialMapper.insert(dto);
	    	MaterialInventoryDto midto = new MaterialInventoryDto(dto.getId(),dto.getNumber());
			if(1==materialInventoryMapper.insert(midto))
				return "1,,新增成功";
			else return "0,,新增失败";
		}else{
			if(1==materialMapper.updateById(dto))
				return "1,,修改成功";
			else return "0,,修改失败";
		}
	}

	@Override
    @Transactional
	public String deleteByKey(String id) {
		if(materialMapper.deleteByKey(id)== materialInventoryMapper.deleteByMaterialId(id))
			return "1,,删除成功";
		else
			return "0,,删除失败";
	}

	@Override
	public PageResponse queryPage(MaterialDto dto) {
		if(dto.getName() !=null &&dto.getName().length()>0)
			dto.setName('%' +dto.getName()+'%');
		return new PageResponse( materialMapper.queryPage(dto) , materialMapper.getCount(dto) ,dto.getDraw());
	}

	@Override
	public Material selectByKey(String id) {
		return materialMapper.selectByKey(id);
	}
}