package cn.edu.zucc.anjone.mrp.info.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zucc.anjone.mrp.info.dto.ProductTypeDto;
import cn.edu.zucc.anjone.mrp.info.service.IProductTypeService;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Controller
@RequestMapping(value="/info/producttype")
public class ProductTypeController {

    private static final Logger logger = LoggerFactory.getLogger(ProductTypeController.class);

    @Autowired
    private IProductTypeService productTypeService;
    
    @RequestMapping("/list")
    public String list(){
        return "/info/producttype/list";
    }

    /*
     * 分页查询
     *
     * @param ProductTypeDto
     */
    @RequestMapping("/list.json")
    @ResponseBody
    public PageResponse listJSON(ProductTypeDto dto){
    	return productTypeService.queryPage(dto);
    }

    /**
     * 显示编辑对话框
     * 
     * @param id
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(String id){
        ModelAndView view = new ModelAndView("/info/producttype/edit");
        if(id!=null||id!="")
        	view.addObject("type", productTypeService.selectByKey(id));
        return view;
    }
    
    /**
     * 保存
     *
     * @param ProductTypeDto
     */
    @RequestMapping(value = "/save")//, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult save(@Valid @RequestBody ProductTypeDto dto)  {
    	return new AjaxResult(productTypeService.save(dto));
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public AjaxResult delete(String id) throws Exception {
    	return new AjaxResult(productTypeService.deleteByKey(id));
    }
}