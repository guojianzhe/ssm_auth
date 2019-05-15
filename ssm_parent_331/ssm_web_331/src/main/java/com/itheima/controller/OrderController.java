package com.itheima.controller;


import com.itheima.domain.Order;
import com.itheima.domain.Product;
import com.itheima.service.OrderService;
import com.itheima.service.ProductService;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){


        ModelAndView modelAndView = new ModelAndView();

        List<Order> orderList = orderService.findAll();

        modelAndView.addObject("orderList",orderList);

        modelAndView.setViewName("order-list");

        return modelAndView;
    }

    @RequestMapping("/addUI")
    public ModelAndView addUI(){
        //查询所有的产品
        List<Product> productList = productService.findAll();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("productList",productList);

        modelAndView.setViewName("order-add");

        return modelAndView;

    }


    @RequestMapping("/save")
    public String save(Order order){

        orderService.save(order);
        return "redirect:/order/findAll";
    }
}
