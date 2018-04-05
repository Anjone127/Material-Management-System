package cn.edu.zucc.anjone.mrp.business.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zucc.anjone.mrp.business.dto.AccountsDto;
import cn.edu.zucc.anjone.mrp.business.excel.AccountsExcel;
import cn.edu.zucc.anjone.mrp.business.service.IAccountsService;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Controller
@RequestMapping("/business/accounts")
public class AccountsController {

    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    @Autowired
    private IAccountsService accountsService;
    
    @RequestMapping("/excel")
    public ModelAndView exportExcel(AccountsDto dto){
        return new ModelAndView( new AccountsExcel() , accountsService.excelModel(dto));
    }
    
    @RequestMapping("/list")
    public String list() throws Exception {
        return "/business/accounts/list";
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public PageResponse listJSON(AccountsDto dto) throws Exception {
    	return accountsService.queryPage(dto);
    }
    
    @RequestMapping("/updateState")
    @ResponseBody
    public AjaxResult updateState(AccountsDto dto) throws Exception {
    	return accountsService.updateState(dto);
    }
}