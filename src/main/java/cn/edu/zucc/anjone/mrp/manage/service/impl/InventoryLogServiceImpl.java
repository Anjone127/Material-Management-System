package cn.edu.zucc.anjone.mrp.manage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zucc.anjone.mrp.manage.dto.InventoryLogDto;
import cn.edu.zucc.anjone.mrp.manage.mapper.InventoryLogMapper;
import cn.edu.zucc.anjone.mrp.manage.service.IInventoryLogService;
import cn.edu.zucc.anjone.mrp.util.PageResponse;


@Service
public class InventoryLogServiceImpl implements IInventoryLogService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryLogServiceImpl.class);

    @Autowired
    private InventoryLogMapper logisticsMapper;

	@Override
	public PageResponse queryPage(InventoryLogDto dto) {
		if(dto.getType() !=null &&dto.getType().length()>0)
			dto.setType('%' +dto.getType()+'%');
		return new PageResponse( logisticsMapper.queryPage(dto) , logisticsMapper.getCount(dto) ,dto.getDraw());
	}
}