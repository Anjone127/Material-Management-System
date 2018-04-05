package cn.edu.zucc.anjone.mrp.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zucc.anjone.mrp.system.dto.UserDto;
import cn.edu.zucc.anjone.mrp.system.model.User;
import cn.edu.zucc.anjone.mrp.system.service.IRoleService;
import cn.edu.zucc.anjone.mrp.system.service.IUserService;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Controller
@RequestMapping("/system/user")
public class UserController {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IRoleService RoleService;
    
    @RequestMapping("/list")
    public ModelAndView list(Model model){
    	ModelAndView view = new ModelAndView("/system/user/list");
    	view.addObject("roleList",RoleService.selectAll());
        return view;
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public PageResponse listJSON(UserDto dto){ 
    	return userService.queryPage(dto);
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(String id){
        ModelAndView view = new ModelAndView("/system/user/edit");
    	view.addObject("roleList",RoleService.selectAll());
        User u = userService.selectByKey(id);
        if(id!=null||id!="")
        	view.addObject("user", userService.selectByKey(id));
        return view;
    }

    @RequestMapping(value = "/save")//, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult save(@Valid @RequestBody UserDto dto)  {
    	switch(userService.save(dto)){
    		case 0: return new AjaxResult("error","新增用户失败");
    		case 1: return new AjaxResult("success","新建成功");
    		case 2: return new AjaxResult("success","保存成功");
    		case 3: return new AjaxResult("error","保存失败");
    	}
    	return new AjaxResult("error","操作失败");
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public AjaxResult delete(String id) throws Exception {
    	if(id==null || id=="")
    		return new AjaxResult("error","未指定用户");
    	if(userService.deleteByKey(id)==1)
    		return new AjaxResult("success","删除用户成功!");
        return new AjaxResult("error","删除用户失败!");
    }
}