package cn.edu.zucc.anjone.mrp.manage.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.manage.dto.InventoryLogDto;
import cn.edu.zucc.anjone.mrp.manage.model.InventoryLog;

public interface InventoryLogMapper {

	int insert(InventoryLog log);

	List<InventoryLogDto> queryPage(InventoryLogDto dto);

	int getCount(InventoryLogDto dto);
}