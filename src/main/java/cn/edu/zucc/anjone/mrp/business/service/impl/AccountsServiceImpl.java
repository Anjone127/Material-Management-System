package cn.edu.zucc.anjone.mrp.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zucc.anjone.mrp.business.dto.AccountsDto;
import cn.edu.zucc.anjone.mrp.business.dto.POrderDto;
import cn.edu.zucc.anjone.mrp.business.mapper.AccountsMapper;
import cn.edu.zucc.anjone.mrp.business.mapper.MOrderMapper;
import cn.edu.zucc.anjone.mrp.business.mapper.POrderMapper;
import cn.edu.zucc.anjone.mrp.business.model.MOrder;
import cn.edu.zucc.anjone.mrp.business.service.IAccountsService;
import cn.edu.zucc.anjone.mrp.info.mapper.CustomerMapper;
import cn.edu.zucc.anjone.mrp.info.mapper.SupplierMapper;
import cn.edu.zucc.anjone.mrp.info.model.Customer;
import cn.edu.zucc.anjone.mrp.info.model.Supplier;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Service
public class AccountsServiceImpl implements IAccountsService {

	private static final Logger logger = LoggerFactory.getLogger(AccountsServiceImpl.class);

	@Autowired
	private AccountsMapper accountsMapper;

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private SupplierMapper supplierMapper;

	@Autowired
	private MOrderMapper morderMapper;

	@Autowired
	private POrderMapper porderMapper;

	@Override
	public PageResponse queryPage(AccountsDto dto){

		//设置查询条件 peopleId 
		if(dto.getPeopleNumber() !=null && !dto.getPeopleNumber().equals("")){
			Customer customer = customerMapper.selectByNumber( dto.getPeopleNumber());
			if(customer==null){
				Supplier supplier =  supplierMapper.selectByNumber( dto.getPeopleNumber());
				if(supplier!=null){
					dto.setPeopleId( supplier.getId());
				}
			}else{
				dto.setPeopleId(customer.getId());
			}
		}

		List<AccountsDto> list = accountsMapper.queryPage(dto);

		//用户名称 编号 订单编号
		for(AccountsDto obj : list){
			if(obj.getType().equals("0")){ //客户
				Customer customer = customerMapper.selectByKey(obj.getPeopleId());
				obj.setPeopleName( customer.getName());
				obj.setPeopleNumber( customer.getNumber());
				POrderDto po = porderMapper.selectByKey( obj.getOrderId());
				obj.setOrderNumber( po.getNumber());
			}
			else{ //供应商
				Supplier supplier =  supplierMapper.selectByKey(obj.getPeopleId());
				obj.setPeopleName(supplier.getName() );
				obj.setPeopleNumber( supplier.getNumber());
				MOrder po = morderMapper.selectByKey( obj.getOrderId());
				obj.setOrderNumber( po.getNumber());

			}
		}
		return new PageResponse( list , accountsMapper.getCount(dto) ,dto.getDraw());
	}

	@Override
	public AjaxResult updateState(AccountsDto dto) {
		accountsMapper.updateState(dto);
		return new AjaxResult("success","操作成功");
	}

	@Override
	public Map<String, Object> excelModel(AccountsDto dto) {

		Map<String, Object> model = new HashMap<>();

		//设置查询条件 peopleId 
		if(dto.getPeopleNumber() !=null && !dto.getPeopleNumber().equals("")){
			Customer customer = customerMapper.selectByNumber( dto.getPeopleNumber());
			if(customer==null){
				Supplier supplier =  supplierMapper.selectByNumber( dto.getPeopleNumber());
				if(supplier!=null){
					dto.setPeopleId( supplier.getId());
				}
			}else{
				dto.setPeopleId(customer.getId());
			}
		}

		List<AccountsDto> list = accountsMapper.selectByLimit(dto);

		//用户名称 编号 订单编号
		for(AccountsDto obj : list){
			if(obj.getType().equals("0")){ //客户
				Customer customer = customerMapper.selectByKey(obj.getPeopleId());
				obj.setPeopleName( customer.getName());
				obj.setPeopleNumber( customer.getNumber());
				POrderDto po = porderMapper.selectByKey( obj.getOrderId());
				obj.setOrderNumber( po.getNumber());
			}
			else{ //供应商
				Supplier supplier =  supplierMapper.selectByKey(obj.getPeopleId());
				obj.setPeopleName(supplier.getName() );
				obj.setPeopleNumber( supplier.getNumber());
				MOrder po = morderMapper.selectByKey( obj.getOrderId());
				obj.setOrderNumber( po.getNumber());

			}
		}

		model.put("list", list);
		return model;
	}

}