package com.itheima;


import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class ProductTest {

    @Autowired
    ProductService productService;
    @Test
    public void test1(){
        List<Product> productList = productService.findAll();
        System.out.println(productList);

    }


    @Test
    public void test2(){

        productService.testFindByPageHelper(2,5);
    }

}
