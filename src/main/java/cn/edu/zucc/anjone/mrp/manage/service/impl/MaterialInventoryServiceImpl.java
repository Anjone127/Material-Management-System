package cn.edu.zucc.anjone.mrp.manage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.anjone.mrp.info.mapper.MaterialMapper;
import cn.edu.zucc.anjone.mrp.info.model.Material;
import cn.edu.zucc.anjone.mrp.manage.dto.InventoryLogDto;
import cn.edu.zucc.anjone.mrp.manage.dto.MaterialInventoryDto;
import cn.edu.zucc.anjone.mrp.manage.mapper.InventoryLogMapper;
import cn.edu.zucc.anjone.mrp.manage.mapper.MaterialInventoryMapper;
import cn.edu.zucc.anjone.mrp.manage.service.IMaterialInventoryService;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Service
public class MaterialInventoryServiceImpl implements IMaterialInventoryService {

    private static final Logger logger = LoggerFactory.getLogger(MaterialInventoryServiceImpl.class);

    @Autowired
    private MaterialInventoryMapper materialInventoryMapper;
    
    @Autowired
    private MaterialMapper materialMapper;
    
    @Autowired
    private InventoryLogMapper logisticsMapper;

	@Override
	public PageResponse queryPage(MaterialInventoryDto dto) {
		if(dto.getName() !=null &&dto.getName().length()>0)
			dto.setName('%' +dto.getName()+'%');
		if(dto.getNumber() !=null &&dto.getNumber().length()>0)
			dto.setNumber('%' +dto.getNumber()+'%');
		return new PageResponse( materialInventoryMapper.queryPage(dto) , materialInventoryMapper.getCount(dto) ,dto.getDraw());
	}

	@Override
	@Transactional
	public String checkAmount(MaterialInventoryDto dto) {
		MaterialInventoryDto pre =  materialInventoryMapper.selectByKey(dto.getId());
		materialInventoryMapper.updateAmountByKey(dto);
		Material material = materialMapper.selectByKey(pre.getMaterialId());
		InventoryLogDto log = new InventoryLogDto("2", material.getName(),material.getId() , dto.getAmount() -pre.getAmount());
		logisticsMapper.insert(log);
		return "1,,操作成功";
	}



	@Override
	public MaterialInventoryDto selectByKey(String id) {
		return materialInventoryMapper.selectByKey(id);
	}
}