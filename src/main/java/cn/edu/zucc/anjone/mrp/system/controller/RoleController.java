package cn.edu.zucc.anjone.mrp.system.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;

import cn.edu.zucc.anjone.mrp.system.dto.RoleDto;
import cn.edu.zucc.anjone.mrp.system.model.Role;
import cn.edu.zucc.anjone.mrp.system.service.IRoleService;
import cn.edu.zucc.anjone.mrp.util.AjaxResult;
import cn.edu.zucc.anjone.mrp.util.PageRequest;
import cn.edu.zucc.anjone.mrp.util.PageResponse;

@Controller
@RequestMapping("/admin/tem/role")
public class RoleController {
    @Autowired
    private IRoleService RoleService;

    @RequestMapping("/list")
    public String list() throws Exception {
        return "role/list";
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public PageResponse listJSON(PageRequest request) throws Exception {
       return null;
    }

    @RequestMapping("/show")
    public ModelAndView show(String id) throws Exception {
        ModelAndView view = new ModelAndView("role/edit");
//        if (StringUtils.isNotEmpty(id)) {
//            Role Role = RoleService.selectByPrimaryKey(id);
//            if (Role != null) {
//                view.addObject("Role", Role);
//            }
//        }
        return view;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult save(@RequestBody RoleDto Role) throws Exception {
//        if (RoleService.insert(Role, Role.getPermisions()) > 0) {
//            return AjaxResult.success("创建角色成功.");
//        }
        return new AjaxResult("0,,未选择删除角色!");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(String id) throws Exception {
//        if (StringUtils.isEmpty(id)) {
//            return new AjaxResult("0,,未选择删除角色!");
//        }
//        if (RoleService.deleteByPrimaryKey(id) > 0) {
//            return new AjaxResult("0,,未选择删除角色!");
//        }
        return new AjaxResult("0,,择删除角色!");
    }

    @RequestMapping("/permisions.json")
    @ResponseBody
    public AjaxResult listRolePermisionJSON(String roleId) throws Exception {
       // List<RolePermision> privilege = RolePermisionService.selectAllRolePermision(roleId);
        return new AjaxResult("0,,未选择删除角色!");
    }
}
