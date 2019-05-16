package com.itheima.controller;


import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.ibatis.annotations.Result;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    RoleService roleService;

    @Autowired
    UserService userService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();

        List<SysUser> userList = userService.findAll();

        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(SysUser sysUser){

        userService.save(sysUser);

        return "redirect:/user/findAll";
    }

    /**
     * @ResponseBody :把结果集转成json以流的形式返回
     * @param username
     * @return
     */
    @RequestMapping("/isUniqueUsername")
    @ResponseBody
    public String isUniqueUsername(String username){

        boolean b = userService.isUniqueUsername(username);

        return String.valueOf(b);
    }


    @RequestMapping("/details")
    public ModelAndView details(Integer userId){
        SysUser user = userService.findById(userId);

        System.out.println(user);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("user",user);

        modelAndView.setViewName("user-show");

        return modelAndView;
    }

    @RequestMapping("/addRoleTuUserUI")
    public ModelAndView addRoleTuUserUI(Integer userId){
        //查询所有的角色
        List<Role> roleList = roleService.findAll();

        //查询所拥有的角色
        SysUser user = userService.findById(userId);

        StringBuffer sb = new StringBuffer();
        List<Role> roleList1 = user.getRoleList();
        for (Role role : roleList1) {
            sb.append(",");
            sb.append(role.getId());
            sb.append(",");
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("roleList",roleList);
        modelAndView.addObject("str",sb.toString());

        modelAndView.setViewName("user-role-add");

        return modelAndView;
    }

}
