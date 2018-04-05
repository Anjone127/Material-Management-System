package cn.edu.zucc.anjone.mrp.business.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zucc.anjone.mrp.business.dto.MOrderDto;
import cn.edu.zucc.anjone.mrp.business.dto.POrderDetailDto;
import cn.edu.zucc.anjone.mrp.business.dto.POrderDto;
import cn.edu.zucc.anjone.mrp.business.model.POrderDetail;
import cn.edu.zucc.anjone.mrp.business.service.IPOrderService;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Controller
@RequestMapping("/business/porder")
public class POrderController{
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(POrderController.class);

    @Autowired
    private IPOrderService porderService;
    
    @RequestMapping("/list")
    public String list() throws Exception {
        return "/business/porder/list";
    }

    @RequestMapping({"/list.json", "list.xml"})
    @ResponseBody
    public PageResponse listJSON(POrderDto dto) throws Exception {
    	return porderService.queryPage(dto);
    }
    
    //订单信息页面	
    @RequestMapping("/show")
    public ModelAndView edit(String id) throws Exception {
    	ModelAndView view = new ModelAndView("/business/porder/edit");
    	view.addObject("porder", porderService.selectOrderByKey(id));
        return view;
    }
    
    //修改|新增订单信息
    @ResponseBody
    @RequestMapping("/save")
    public AjaxResult save(@RequestBody POrderDto dto) throws Exception {
    	return porderService.saveOrder(dto);
    }
    
    //订单详情分页json数据
    @RequestMapping("/detailList.json")
    @ResponseBody
    public PageResponse detailList(POrderDetailDto dto) throws Exception {
    	return porderService.queryPageDetailList(dto);
    }
    
    //添加订单详情
    @ResponseBody
    @RequestMapping("/addDetail")
    public AjaxResult addDetail(POrderDetail detail) throws Exception {
    	return porderService.saveDetial(detail);
    }
    
    //修改订单状态
    @RequestMapping("/updateState")
    @ResponseBody
    public AjaxResult updateState(POrderDto dto) throws Exception {
    	return porderService.updateState(dto);
    }
    
    //删除订单详情
    @ResponseBody
    @RequestMapping("/deleteDetail")
    public AjaxResult deleteDetail(String id) throws Exception {
    	return porderService.deleteDetailById(id);
    }
}