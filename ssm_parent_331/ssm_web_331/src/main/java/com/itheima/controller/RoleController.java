package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    PermissionService permissionService;

    @Autowired
    RoleService roleService;


    @RequestMapping("findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();

        List<Role> roleList = roleService.findAll();

        modelAndView.addObject("roleList",roleList);

        modelAndView.setViewName("role-list");

        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Role role){
        System.out.println(role);
        roleService.save(role);

        return "redirect:/role/findAll";
    }

    @RequestMapping("/addPermissionToRoleUI")
    public ModelAndView addPermissionToRoleUI(Integer roleId){

        //查询所有的权限
        List<Permission> permissionList = permissionService.findAll();

        //查询角色中所拥有的权限

        List<Permission> permissionList1 = permissionService.findPermissionListByRoleId(roleId);

        StringBuffer sb = new StringBuffer();
        for (Permission permission : permissionList1) {
            sb.append(",");
            sb.append(permission.getId());
            sb.append(",");
        }


        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("permissionList",permissionList);
        modelAndView.addObject("str",sb.toString());
        modelAndView.addObject("roleId",roleId);

        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(Integer roleId,Integer[] ids){

        roleService.addPermissionToRole(roleId,ids);


        return "redirect:/role/findAll";
    }


}
