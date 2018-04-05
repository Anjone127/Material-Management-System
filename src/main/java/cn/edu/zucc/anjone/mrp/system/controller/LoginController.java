package cn.edu.zucc.anjone.mrp.system.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.zucc.anjone.mrp.system.dto.UserDto;
import cn.edu.zucc.anjone.mrp.system.service.IUserService;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)  
	public String login() {
		return "/login";
	}
	
	@RequestMapping(value = "/index")  
	public String index() {
  		return "/system/index";
  	}
  	
  	@RequestMapping(value = "/login", method=RequestMethod.POST)  
  	public String menu(UserDto user) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserId(), user.getUserPassword());   
        Subject subject = SecurityUtils.getSubject();
        try{
        	subject.login(token);
        }catch(Exception e){
        	e.printStackTrace();
        	return "/login";
        }
  		return "redirect:/menu";
  	}
  	
  	@RequestMapping(value = "/menu")  
  	public String menu() {
        return "/system/menu";
  	}
  	
  	@RequestMapping(value = "/logout")  
  	public String logout() {   
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/login";
  	}
}
