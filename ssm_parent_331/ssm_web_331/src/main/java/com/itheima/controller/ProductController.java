package com.itheima.controller;



import com.github.pagehelper.PageInfo;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * mybatis分页助手查询
     * @param currPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(
            @RequestParam(value = "currPage",required = false,defaultValue = "1") Integer currPage,
            @RequestParam(value = "pageSize",required = false,defaultValue = "5") Integer pageSize){
        //准备数据
        PageInfo<Product> pageInfo = productService.findByPageHelper(currPage,pageSize);

        //创建ModelAndView
        ModelAndView modelAndView = new ModelAndView();
//
        modelAndView.addObject("pageInfo",pageInfo);

        //返回视图
        modelAndView.setViewName("product-list-zidong");

        return modelAndView;
    }
    /**
     * 手动分页查询全部
     *
     *
     * @RequestParam 请求参数绑定
     *          name:别名value,指定页面参数的名称
     * @return
     */
    @RequestMapping("/findAll3")
    public ModelAndView findAll3(
            @RequestParam(value = "currPage",required = false,defaultValue = "1") Integer currPage,
            @RequestParam(value = "pageSize",required = false,defaultValue = "5") Integer pageSize){
        //准备数据
        PageBean<Product> pageBean = productService.findByPage(currPage,pageSize);

        //创建ModelAndView
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("pageBean",pageBean);

        //返回视图
        modelAndView.setViewName("product-list");

        return modelAndView;
    }

    /**
     * 查询全部
     * @return
     */
    @RequestMapping("/findAll2")
    public ModelAndView findAll2(){
        //准备数据
        List<Product> productList = productService.findAll();

        //创建ModelAndView
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("productList",productList);

        //返回视图
        modelAndView.setViewName("product-list");

        return modelAndView;
    }

    /**
     * 产品保存页面
     * @param product
     * @return
     */
    @RequestMapping("/save")
    public String save(Product product){

        productService.save(product);

        return "redirect:/product/findAll";
    }

    @RequestMapping("/updateUI")
    public ModelAndView updateUI(Integer id){


        Product product = productService.findById(id);


        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("product",product);

        modelAndView.setViewName("product-update");

        return modelAndView;
    }

    @RequestMapping("/update")
    public String update(Product product){

        productService.update(product);


        return "redirect:/product/findAll";
    }

    @RequestMapping("/deleteOne")
    public String deleteOne(Integer id){

        productService.deleteOne(id);

        return "redirect:/product/findAll";
    }

    @RequestMapping("/delMany")
    public String delMany(Integer[] ids){

            productService.delMany(ids);


        return "redirect:/product/findAll";
    }



}
