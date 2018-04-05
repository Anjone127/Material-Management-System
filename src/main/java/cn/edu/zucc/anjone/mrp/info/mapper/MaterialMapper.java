package cn.edu.zucc.anjone.mrp.info.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.info.dto.MaterialDto;
import cn.edu.zucc.anjone.mrp.info.model.Material;

public interface MaterialMapper {
	int insert(MaterialDto dto);
	
    Material selectByKey(String id);

    Material selectByNumber(String id);
    
    List<Material> queryPage(MaterialDto dto);
    
    int updateById(MaterialDto dto);
    
    int deleteByKey(String id);
    
    int getCount(MaterialDto dto);

}