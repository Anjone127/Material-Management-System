package cn.edu.zucc.anjone.mrp.business.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zucc.anjone.mrp.business.dto.AccountsDto;
import cn.edu.zucc.anjone.mrp.business.dto.MOrderDetailDto;
import cn.edu.zucc.anjone.mrp.business.dto.MOrderDto;
import cn.edu.zucc.anjone.mrp.business.mapper.AccountsMapper;
import cn.edu.zucc.anjone.mrp.business.mapper.MOrderDetailMapper;
import cn.edu.zucc.anjone.mrp.business.mapper.MOrderMapper;
import cn.edu.zucc.anjone.mrp.business.model.MOrder;
import cn.edu.zucc.anjone.mrp.business.model.MOrderDetail;
import cn.edu.zucc.anjone.mrp.business.service.IMOrderService;
import cn.edu.zucc.anjone.mrp.info.mapper.MaterialMapper;
import cn.edu.zucc.anjone.mrp.info.mapper.SupplierMapper;
import cn.edu.zucc.anjone.mrp.info.model.Material;
import cn.edu.zucc.anjone.mrp.manage.dto.InventoryLogDto;
import cn.edu.zucc.anjone.mrp.manage.mapper.InventoryLogMapper;
import cn.edu.zucc.anjone.mrp.manage.mapper.MaterialInventoryMapper;
import cn.edu.zucc.anjone.mrp.manage.model.MaterialInventory;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Service
public class MOrderServiceImpl implements IMOrderService{

	/**
	 * 日志记录器
	 */
	private static final Logger logger = LoggerFactory.getLogger(MOrderServiceImpl.class);

	@Autowired
	private MOrderMapper morderMapper;

	@Autowired
	private SupplierMapper supplierMapper;
	
	@Autowired
	private MOrderDetailMapper mOrderDetailMapper;
	
	@Autowired
	private MOrderMapper mOrderMapper;
	
    @Autowired
    private MaterialMapper materialMapper;
    
    @Autowired
    private MaterialInventoryMapper materialInventoryMapper;
    
    @Autowired
    private AccountsMapper accountsMapper;
    
    @Autowired
    private InventoryLogMapper logisticsMapper;

	@Override
	public PageResponse queryPage(MOrderDto dto) {
		return new PageResponse( morderMapper.queryPage(dto) , morderMapper.getCount(dto) ,dto.getDraw());
	}

	@Override
	public MOrder selectByKey(String id) {
		return morderMapper.selectByKey(id);
	}

	@Override
	public synchronized AjaxResult save(MOrderDto dto) {
		if(morderMapper.selectByKey(dto.getId())==null){
			if(morderMapper.selectByNumber(dto.getNumber())!=null)
					return new AjaxResult("0,,订单编号已存在");
			if(supplierMapper.selectByNumber(dto.getSupplierNumber())==null)
				return new AjaxResult("0,,供应商编号不存在");
			dto.setStartTime(new Date());
			dto.setFinalTime(new Date());
			if(morderMapper.insert(dto) > 0)
				return new AjaxResult("1,,添加成功");
		}else if(morderMapper.updateRemark(dto) > 0){
			return new AjaxResult("1,,修改成功");
		}
		return new AjaxResult("0,,添加失败");
	}
	
	//订单详情保存
	@Override
	@Transactional
	public synchronized AjaxResult saveDetial(MOrderDetail detail) {
		try{
			MOrderDetail d = mOrderDetailMapper.selectByMaterialNumberAndOrderId(detail);
			//更新详情
			if(d==null){
				mOrderDetailMapper.insert(detail);
			}else{
				d.setAmount( d.getAmount() + detail.getAmount());
				mOrderDetailMapper.updateAmountByKey(d);
			}
			//更新订单总金额
			MOrder order = mOrderMapper.selectByKey(detail.getOrderId());
			MOrderDto dto =new  MOrderDto();
			dto.setId(order.getId());
			dto.setTotalMoney(order.getTotalMoney() + detail.getAmount()*detail.getPrice());
			mOrderMapper.updateTotalMoney(dto);
			return new AjaxResult("1,,操作成功");
		}catch(Exception e){
			return new AjaxResult("0,,操作失败");
		}
		
	}

	public PageResponse queryPageDetailList(MOrderDetailDto dto) {
		return new PageResponse( mOrderDetailMapper.queryPage(dto) , mOrderDetailMapper.getCount(dto) ,dto.getDraw());
	}

	@Override
	@Transactional
	public synchronized AjaxResult deleteDetailById(String id) {
		MOrderDetail detail = mOrderDetailMapper.selectByKey(id);
		MOrder order = mOrderMapper.selectByKey(detail.getOrderId());
		MOrderDto dto =new  MOrderDto();
		dto.setId(order.getId());
		dto.setTotalMoney(order.getTotalMoney() - detail.getAmount()*detail.getPrice());
		mOrderMapper.updateTotalMoney(dto);
		mOrderDetailMapper.deleteByKey(id);
		return new AjaxResult("1,,操作成功");
	}
	
	//更新新单状态
	@Override
	@Transactional
	public synchronized AjaxResult updateState(MOrderDto dto) {
		switch(dto.getState()){
			case "3":{		// 订单确认 货物入库
				MaterialInventory inventory = null;
				Material material = null;
				List<MOrderDetail> list = mOrderDetailMapper.selectByOrderId(dto.getId());
				for(MOrderDetail detial : list){
					inventory = materialInventoryMapper.selectByMaterialId(detial.getMaterialId());
					inventory.setAmount(detial.getAmount() + inventory.getAmount());
					inventory.setMaterialId(detial.getMaterialId());
					materialInventoryMapper.updateAmountByMaterialId(inventory);
					//日志记录
					material = materialMapper.selectByKey(detial.getMaterialId());
					InventoryLogDto log = new InventoryLogDto("0", material.getName(),material.getId() , detial.getAmount());
					logisticsMapper.insert(log);
				}
				break;
			}
			case "1" :{ //确认订单 待收货状态
				MOrder order = mOrderMapper.selectByKey( dto.getId() );
				AccountsDto adto = new AccountsDto();
				adto.setDate( new Date());
				adto.setAmount( order.getTotalMoney());
				adto.setOrderId( order.getId());
				adto.setPeopleId( supplierMapper.selectByNumber( order.getSupplierNumber()).getId() );
				adto.setRemark("");
				adto.setState("0");
				adto.setType("1");
				accountsMapper.insert(adto);
				break;
			}
		}
		if(morderMapper.updateState(dto)>0)
			return new AjaxResult("1,,确认成功");
		return null;
	}

}
