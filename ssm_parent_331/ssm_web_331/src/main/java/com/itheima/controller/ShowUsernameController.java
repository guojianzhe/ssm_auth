package com.itheima.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
public class ShowUsernameController {

    @RequestMapping("/showUsername")
    public void showUsername(HttpServletRequest request){
        //获取session对象
        HttpSession session = request.getSession();
        //从session域中获取
        //从session域中获取所有的属性名
        //枚举类型:遍历枚举类型
        Enumeration attributeNames = session.getAttributeNames();
        //判断是否有更多的元素

        while (attributeNames.hasMoreElements()){
            //获取枚举中的下一个元素
            System.out.println(attributeNames.nextElement());
        }
        //SPRING_SECURITY_CONTEXT:存储用户登录信息的session中的名称

        Object spring_security_context = session.getAttribute("SPRING_SECURITY_CONTEXT");
        //System.out.println(spring_security_context);
        //获取的是spring安全框架中的SecurityContext上下文对象
        SecurityContext securityContext = (SecurityContext)spring_security_context;
        //获取认证信息
        Authentication authentication = securityContext.getAuthentication();
        //getPrincipal //翻译:重要的--获取重要的信息  用户详情
        User user = (User) authentication.getPrincipal();

        String username = user.getUsername();
        System.out.println(username);


        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication1 = context.getAuthentication();
        User user1 = (User)authentication.getPrincipal();
        System.out.println(user.getUsername());
    }
}
