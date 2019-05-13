package com.itheima.controller;



import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        //准备数据
        List<Product> productList = productService.findAll();

        //创建ModelAndView
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("productList",productList);

        //返回视图
        modelAndView.setViewName("product-list");

        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Product product){

        productService.save(product);

        return "redirect:/product/findAll";
    }

}
