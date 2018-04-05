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
import cn.edu.zucc.anjone.mrp.business.dto.ProductionDto;
import cn.edu.zucc.anjone.mrp.business.service.IProductionService;
import cn.edu.zucc.anjone.mrp.manage.controller.InventoryLogController;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Controller
@RequestMapping("/business/production")
public class ProductionController {
	
	private static final Logger logger = LoggerFactory.getLogger(InventoryLogController.class);

    @Autowired
    private IProductionService productionService;
    
    @RequestMapping("/list")
    public String list() throws Exception {
        return "/business/production/list";
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public PageResponse listJSON(ProductionDto dto) throws Exception {
    	return productionService.queryPage(dto);
    }
    
    //信息页面
    @RequestMapping("/show")
    public ModelAndView edit(String number) throws Exception {
    	ModelAndView view = new ModelAndView("/business/production/edit");
    	view.addObject("productNumber", number);
        return view;
    }
    
    //新增
    @ResponseBody
    @RequestMapping("/save")
    public AjaxResult save(@RequestBody ProductionDto dto) throws Exception {
    	return productionService.save(dto);
    }
    
    //新增
    @ResponseBody
    @RequestMapping("/updateState")
    public AjaxResult updateState(ProductionDto dto) throws Exception {
    	return productionService.updateState(dto);
    }
}
