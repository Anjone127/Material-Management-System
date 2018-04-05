package cn.edu.zucc.anjone.mrp.info.mapper;

import java.util.List;

import cn.edu.zucc.anjone.mrp.info.dto.SupplierDto;
import cn.edu.zucc.anjone.mrp.info.model.Supplier;

public interface SupplierMapper {
	int insert(SupplierDto dto);
	
    Supplier selectByKey(String id);

    Supplier selectByNumber(String number);
    
    Supplier selectByName(String name);
    
    List<Supplier> queryPage(SupplierDto dto);
    
    int updateById(SupplierDto dto);
    
    int deleteByKey(String id);
    
    int getCount(SupplierDto dto);

}