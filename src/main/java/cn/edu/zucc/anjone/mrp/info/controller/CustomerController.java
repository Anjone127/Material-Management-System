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

import cn.edu.zucc.anjone.mrp.info.dto.CustomerDto;
import cn.edu.zucc.anjone.mrp.info.service.ICustomerService;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Controller
@RequestMapping(value="/info/customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private ICustomerService costomerService;
    

    @RequestMapping("/list")
    public String list() throws Exception {
        return "/info/customer/list";
    }


    @RequestMapping("/list.json")
    @ResponseBody
    public PageResponse listJSON(CustomerDto dto) throws Exception {
    	return costomerService.queryPage(dto);
    }
    

    /**
     * 显示编辑对话框
     * 
     * @param id
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(String id){
        ModelAndView view = new ModelAndView("/info/customer/edit");
        if(id!=null||id!="")
        	view.addObject("customer", costomerService.selectByKey(id));
        return view;
    }
    
    /**
     * 保存
     *
     * @param CustomerDto
     */
    @RequestMapping(value = "/save")//, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult save(@Valid @RequestBody CustomerDto dto)  {
    	AjaxResult a = new AjaxResult(costomerService.save(dto));
    	return a;
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public AjaxResult delete(String id) throws Exception {
    	return new AjaxResult(costomerService.deleteByKey(id));
    }
}