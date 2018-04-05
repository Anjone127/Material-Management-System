package cn.edu.zucc.anjone.mrp.info.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zucc.anjone.mrp.info.dto.CustomerDto;
import cn.edu.zucc.anjone.mrp.info.mapper.CustomerMapper;
import cn.edu.zucc.anjone.mrp.info.model.Customer;
import cn.edu.zucc.anjone.mrp.info.service.ICustomerService;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Service
public class CustomerServiceImpl implements ICustomerService {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerMapper costomerMapper;

	@Override
	public String save(CustomerDto dto) {
		if(dto.getId()==""||dto.getId()==null){
			if(null != costomerMapper.selectByNumber(dto.getNumber()))
				return "0,,新增失败,编号已存在";
			dto.setMoney(0.0);
			if(1==costomerMapper.insert(dto))
				return "1,,新增成功";
			else return "0,,新增失败";
		}else{
			if(1==costomerMapper.updateById(dto))
				return "1,,修改成功";
			else return "0,,修改失败";
		}
	}

	@Override
	public String deleteByKey(String id) {
		if(costomerMapper.deleteByKey(id)==1)
			return "1,,删除成功";
		else
			return "0,,删除失败";
	}
	
	@Override
	public PageResponse queryPage(CustomerDto dto) {
	if(dto.getName() !=null &&dto.getName().length()>0)
		dto.setName('%' +dto.getName()+'%');
	if(dto.getName() !=null &&dto.getNumber().length()>0)
		dto.setNumber('%' +dto.getNumber()+'%');
		return new PageResponse( costomerMapper.queryPage(dto) , costomerMapper.getCount(dto) ,dto.getDraw());
	}

	@Override
	public Customer selectByKey(String id) {
		return costomerMapper.selectByKey(id);
	}
}