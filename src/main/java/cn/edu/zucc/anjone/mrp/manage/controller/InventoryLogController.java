package cn.edu.zucc.anjone.mrp.manage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zucc.anjone.mrp.manage.dto.InventoryLogDto;
import cn.edu.zucc.anjone.mrp.manage.service.IInventoryLogService;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Controller
@RequestMapping(value="/manage/lnventoryLog")
public class InventoryLogController {

    private static final Logger logger = LoggerFactory.getLogger(InventoryLogController.class);

    @Autowired
    private IInventoryLogService lnventoryLogService;
    
    @RequestMapping("/list")
    public String list() throws Exception {
        return "/manage/lnventoryLog/list";
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public PageResponse listJSON(InventoryLogDto dto) throws Exception {
    	return lnventoryLogService.queryPage(dto);
    }
}