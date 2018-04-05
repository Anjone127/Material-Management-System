package cn.edu.zucc.anjone.mrp.manage.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zucc.anjone.mrp.manage.dto.MaterialInventoryDto;
import cn.edu.zucc.anjone.mrp.manage.dto.ProductInventoryDto;
import cn.edu.zucc.anjone.mrp.manage.service.IProductInventoryService;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Controller
@RequestMapping(value="/manage/productInventory")
public class ProductInventoryController {

	private static final Logger logger = LoggerFactory.getLogger(ProductInventoryController.class);

	@Autowired
	private IProductInventoryService productInventoryService;

	@RequestMapping("/list")
	public String list() throws Exception {
		return "/manage/productInventory/list";
	}

	@RequestMapping("/list.json")
	@ResponseBody
	public PageResponse listJSON(ProductInventoryDto dto) throws Exception {
		return productInventoryService.queryPage(dto);
	}
	
    @RequestMapping("/show")
    public ModelAndView show(String id) throws Exception {
    	ModelAndView view = new ModelAndView("/manage/productInventory/edit");
    	view.addObject("productInventory", productInventoryService.selectByKey(id));
        return view;
    }
    
    @RequestMapping("/check")
    @ResponseBody
    public AjaxResult check(@RequestBody @Valid ProductInventoryDto dto) throws Exception {
    	return new AjaxResult(productInventoryService.checkAmount(dto));
    }
}