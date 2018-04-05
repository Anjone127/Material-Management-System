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
import cn.edu.zucc.anjone.mrp.manage.service.IMaterialInventoryService;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Controller
@RequestMapping(value="/manage/materialInventory")
public class MaterialInventoryController {

    private static final Logger logger = LoggerFactory.getLogger(MaterialInventoryController.class);

    @Autowired
    private IMaterialInventoryService materialInventoryService;
    
    @RequestMapping("/list")
    public String list() throws Exception {
        return "/manage/materialInventory/list";
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public PageResponse listJSON(MaterialInventoryDto dto) throws Exception {
    	return materialInventoryService.queryPage(dto);
    }
    
    @RequestMapping("/show")
    public ModelAndView show(String id) throws Exception {
    	ModelAndView view = new ModelAndView("/manage/materialInventory/edit");
    	view.addObject("materialInventory", materialInventoryService.selectByKey(id));
        return view;
    }
    
    @RequestMapping("/check")
    @ResponseBody
    public AjaxResult check(@RequestBody @Valid MaterialInventoryDto dto) throws Exception {
    	return new AjaxResult(materialInventoryService.checkAmount(dto));
    }
}