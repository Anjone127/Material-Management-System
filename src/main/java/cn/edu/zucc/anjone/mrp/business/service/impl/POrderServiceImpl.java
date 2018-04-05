package cn.edu.zucc.anjone.mrp.business.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zucc.anjone.mrp.business.dto.AccountsDto;
import cn.edu.zucc.anjone.mrp.business.dto.POrderDetailDto;
import cn.edu.zucc.anjone.mrp.business.dto.POrderDto;
import cn.edu.zucc.anjone.mrp.business.mapper.AccountsMapper;
import cn.edu.zucc.anjone.mrp.business.mapper.POrderDetailMapper;
import cn.edu.zucc.anjone.mrp.business.mapper.POrderMapper;
import cn.edu.zucc.anjone.mrp.business.model.POrderDetail;
import cn.edu.zucc.anjone.mrp.business.service.IPOrderService;
import cn.edu.zucc.anjone.mrp.info.mapper.CustomerMapper;
import cn.edu.zucc.anjone.mrp.info.mapper.ProductMapper;
import cn.edu.zucc.anjone.mrp.info.model.Customer;
import cn.edu.zucc.anjone.mrp.info.model.Product;
import cn.edu.zucc.anjone.mrp.manage.dto.InventoryLogDto;
import cn.edu.zucc.anjone.mrp.manage.mapper.InventoryLogMapper;
import cn.edu.zucc.anjone.mrp.manage.mapper.ProductInventoryMapper;
import cn.edu.zucc.anjone.mrp.manage.model.ProductInventory;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;


@Service
public class POrderServiceImpl  implements IPOrderService {

	@Autowired
	private POrderMapper porderMapper;

	@Autowired
	private CustomerMapper costomerMapper;

	@Autowired
	private POrderDetailMapper pOrderDetailMapper;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ProductInventoryMapper productInventoryMapper;

	@Autowired
	private InventoryLogMapper logisticsMapper;
	
    @Autowired
    private AccountsMapper accountsMapper;
    
	@Override
	public PageResponse queryPage(POrderDto dto) {
		return new PageResponse( porderMapper.queryPage(dto) , porderMapper.getCount(dto) ,dto.getDraw());
	}

	@Override
	public POrderDto selectOrderByKey(String id) {
		return porderMapper.selectByKey(id);
	}

	@Override
	public AjaxResult saveOrder(POrderDto dto) {
		if(porderMapper.selectByKey(dto.getId())==null){
			if(porderMapper.selectByNumber(dto.getNumber())!=null)
				return new AjaxResult("0,,订单编号已存在");
			Customer c = costomerMapper.selectByNumber(dto.getCustomerNumber());
			if(c==null)
				return new AjaxResult("0,,客户编号不存在");
			dto.setStartTime(new Date());
			dto.setFinalTime(new Date());
			if(dto.getAddress()==null||dto.getAddress().equals("")){
				dto.setAddress(c.getAddress());
			}
			if(porderMapper.insert(dto) > 0)
				return new AjaxResult("1,,添加成功");
		}
		return new AjaxResult("0,,添加失败");
	}

	@Override
	public PageResponse queryPageDetailList(POrderDetailDto dto) {
		return new PageResponse( pOrderDetailMapper.queryPage(dto) , pOrderDetailMapper.getCount(dto) ,dto.getDraw());
	}

	//保存订单详情
	@Override
	@Transactional
	public synchronized AjaxResult saveDetial(POrderDetail detail) {
		POrderDetail d = pOrderDetailMapper.selectByProductNumberAndOrderId(detail);
		POrderDto order = porderMapper.selectByKey(detail.getOrderId());
		if(d==null){
			pOrderDetailMapper.insert(detail);
		}else{
			d.setAmount( d.getAmount() + detail.getAmount());
			pOrderDetailMapper.updateAmountByKey(d);
		}
		order.setTotalMoney( order.getTotalMoney() + detail.getAmount()*detail.getPrice());
		porderMapper.updateTotalMoney(order);
		return new AjaxResult("1,,操作成功");
	}

	//删除订单详情
	@Override
	@Transactional
	public synchronized AjaxResult deleteDetailById(String id) {
		POrderDetail detail = pOrderDetailMapper.selectByKey(id);
		POrderDto order = porderMapper.selectByKey(detail.getOrderId());
		order.setTotalMoney( order.getTotalMoney() - detail.getAmount()*detail.getPrice());
		porderMapper.updateTotalMoney(order);
		if(pOrderDetailMapper.deleteByKey(id)>0)
			return new AjaxResult("1,,操作成功");
		return new AjaxResult("0,,操作失败");
	}

	@Override
	@Transactional
	public synchronized AjaxResult updateState(POrderDto dto) {
		// 1确认订单 3确认收货 4订单撤销
		porderMapper.updateState(dto);
		switch(dto.getState()){
			case "2":{		// 2 订单确认 产品出库
				ProductInventory inventory = null;
				Product product = null;
				List<POrderDetail> list = pOrderDetailMapper.selectByOrderId(dto.getId());
				//库存是否够
				for(POrderDetail detial : list){
					inventory = productInventoryMapper.selectByProductId(detial.getProductId());
					if(inventory.getAmount()-detial.getAmount() <0){
						return new AjaxResult("0,,产品编号" + detial.getProductNumber() +":库存不足");
					}
				}
				for(POrderDetail detial : list){
					inventory = productInventoryMapper.selectByProductId(detial.getProductId());
					inventory.setAmount(inventory.getAmount()-detial.getAmount());
					inventory.setProductId(detial.getProductId());
					productInventoryMapper.updateAmountByProductId(inventory);

					//日志记录
					product = productMapper.selectByKey(detial.getProductId());
					InventoryLogDto log = new InventoryLogDto("4", product.getName(),product.getId() , detial.getAmount());
					logisticsMapper.insert(log);
				}
				break;
			}
			case "1":{//生产账款
				dto = porderMapper.selectByKey(dto.getId());
				AccountsDto adto = new AccountsDto();
				adto.setDate( new Date());
				adto.setAmount( dto.getTotalMoney());
				adto.setOrderId( dto.getId());
				adto.setPeopleId( costomerMapper.selectByNumber(dto.getCustomerNumber()).getId() );
				adto.setRemark("");
				adto.setState("0");
				adto.setType("0");
				accountsMapper.insert(adto);
			}
		}
		return new AjaxResult("1,,操作成功");
	}
}