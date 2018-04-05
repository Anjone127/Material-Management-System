package cn.edu.zucc.anjone.mrp.info.controller;

import java.util.List;

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

import cn.edu.zucc.anjone.mrp.info.dto.ProductDetailDto;
import cn.edu.zucc.anjone.mrp.info.dto.ProductDto;
import cn.edu.zucc.anjone.mrp.info.mapper.ProductTypeMapper;
import cn.edu.zucc.anjone.mrp.info.model.ProductType;
import cn.edu.zucc.anjone.mrp.info.service.IProductService;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Controller
@RequestMapping(value="/info/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private IProductService productService;
    
    @Autowired
    private ProductTypeMapper productTypeMapper;
    
    @RequestMapping("/list")
    public ModelAndView list(){
    	ModelAndView view = new ModelAndView( "/info/product/list");
    	List<ProductType> list = productTypeMapper.selectAll();
    	view.addObject("typeList", productTypeMapper.selectAll());
        return view;
    }
    
    //产品详细原材料json数据
    @RequestMapping("/detailList.json")
    @ResponseBody
    public PageResponse detailListJSON(ProductDetailDto dto){
    	return productService.queryPageDetiaList(dto);
    }
    
    //添加产品详细
    @RequestMapping("/saveDetail")
    @ResponseBody
    public AjaxResult saveDetail(ProductDetailDto dto){
    	return productService.saveDetail(dto);
    }
    
    @RequestMapping("/list.json")
    @ResponseBody
    public PageResponse listJSON(ProductDto dto){
    	return productService.queryPage(dto);
    }
    
    @RequestMapping("/getCost")
    @ResponseBody
    public Double getCost(String id){
    	return productService.selectByKey(id).getCost();
    }
    
    //显示编辑对话框
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(String id){
        ModelAndView view = new ModelAndView("/info/product/edit");
        if(id!=null||id!="")
        	view.addObject("product", productService.selectByKey(id));
        view.addObject("typeList", productTypeMapper.selectAll());
        return view;
    }
    
    //保存产品信息
    @RequestMapping(value = "/save")//, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult save(@Valid @RequestBody ProductDto dto)  {
    	return productService.save(dto);
    }

    //删除产品信息
    @RequestMapping(value = "/delete")
    @ResponseBody
    public AjaxResult delete(String id) throws Exception {
    	return new AjaxResult(productService.deleteByKey(id));
    }
}
