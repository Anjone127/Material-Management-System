package cn.edu.zucc.anjone.mrp.business.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zucc.anjone.mrp.business.dto.MOrderDetailDto;
import cn.edu.zucc.anjone.mrp.business.dto.MOrderDto;
import cn.edu.zucc.anjone.mrp.business.model.MOrderDetail;
import cn.edu.zucc.anjone.mrp.business.service.IMOrderService;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Controller
@RequestMapping("/business/morder")
public class MOrderController{
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(MOrderController.class);

    @Autowired
    private IMOrderService morderService;
    
    @RequestMapping("/list")
    public String list() throws Exception {
        return "/business/morder/list";
    }
    
    //原材料订单分页json数据
    @RequestMapping({"/list.json", "list.xml"})
    @ResponseBody
    public PageResponse listJSON(MOrderDto dto) throws Exception {
    	return morderService.queryPage(dto);
    }
    
    //订单信息页面
    @RequestMapping("/show")
    public ModelAndView edit(String id) throws Exception {
    	ModelAndView view = new ModelAndView("/business/morder/edit");
    	view.addObject("morder", morderService.selectByKey(id));
        return view;
    }
    
    //修改|新增订单信息
    @ResponseBody
    @RequestMapping("/save")
    public AjaxResult save(@RequestBody MOrderDto dto) throws Exception {
    	return morderService.save(dto);
    }
    
    //添加订单详情
    @ResponseBody
    @RequestMapping("/addDetail")
    public AjaxResult addDetail(MOrderDetail detail) throws Exception {
    	return morderService.saveDetial(detail);
    }
    
    //删除订单详情
    @ResponseBody
    @RequestMapping("/deleteDetail")
    public AjaxResult deleteDetail(String id) throws Exception {
    	return morderService.deleteDetailById(id);
    }
    
    //订单详情分页json数据
    @RequestMapping("/detailList.json")
    @ResponseBody
    public PageResponse detailList(MOrderDetailDto dto) throws Exception {
    	return morderService.queryPageDetailList(dto);
    }
    
    //修改订单状态
    @RequestMapping("/updateState")
    @ResponseBody
    public AjaxResult updateState(MOrderDto dto) throws Exception {
    	return morderService.updateState(dto);
    }
}