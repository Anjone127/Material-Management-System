package cn.edu.zucc.anjone.mrp.info.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zucc.anjone.mrp.info.dto.SupplierDto;
import cn.edu.zucc.anjone.mrp.info.mapper.SupplierMapper;
import cn.edu.zucc.anjone.mrp.info.model.Supplier;
import cn.edu.zucc.anjone.mrp.info.service.ISupplierService;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Service
public class SupplierServiceImpl implements ISupplierService {

    private static final Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

    @Autowired
    private SupplierMapper supplierMapper;

	@Override
	public String save(SupplierDto dto) {
		if(dto.getId()==""||dto.getId()==null){
			if(null != supplierMapper.selectByNumber(dto.getNumber()))
				return "0,,新增失败,编号已存在";
			dto.setMoney(0.0);
			if(1==supplierMapper.insert(dto))
				return "1,,新增成功";
			else return "0,,新增失败";
		}else{
			if(1==supplierMapper.updateById(dto))
				return "1,,修改成功";
			else return "0,,修改失败";
		}
	}

	@Override
	public String deleteByKey(String id) {
		if(supplierMapper.deleteByKey(id)==1)
			return "1,,删除成功";
		else
			return "0,,删除失败";
	}

	@Override
	public PageResponse queryPage(SupplierDto dto) {
		if(dto.getName() !=null &&dto.getName().length()>0)
			dto.setName('%' +dto.getName()+'%');
		if(dto.getName() !=null &&dto.getNumber().length()>0)
			dto.setNumber('%' +dto.getNumber()+'%');
		return new PageResponse( supplierMapper.queryPage(dto) , supplierMapper.getCount(dto) ,dto.getDraw());
	}

	@Override
	public Supplier selectByKey(String id) {
		return supplierMapper.selectByKey(id);
	}

	@Override
	public AjaxResult selectByInfo(String info) {
		Supplier s = supplierMapper.selectByNumber(info);
		if(s==null)
			s = supplierMapper.selectByName(info);
		if(s==null)
			return new AjaxResult("error","查不到符合条件的供应商");
		return new AjaxResult("success",s.getNumber());
	}

}