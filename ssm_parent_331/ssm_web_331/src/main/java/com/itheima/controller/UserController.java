package com.itheima.controller;


import com.itheima.domain.SysUser;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

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

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("user",user);

        modelAndView.setViewName("user-show");

        return modelAndView;
    }

}
