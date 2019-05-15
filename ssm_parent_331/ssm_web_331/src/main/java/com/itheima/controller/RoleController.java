package com.itheima.controller;

import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

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

}
