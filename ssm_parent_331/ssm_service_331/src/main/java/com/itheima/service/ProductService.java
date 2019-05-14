package com.itheima.service;


import com.github.pagehelper.PageInfo;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;

import java.util.List;

public interface ProductService {


    public List<Product> findAll();

    public void save(Product product);

    public Product findById(Integer id);

    public void update(Product product);

    public void deleteOne(Integer id);

    public void delMany(Integer[] ids);

    public PageBean<Product> findByPage(Integer currPage, Integer pageSize);

    public PageInfo<Product> findByPageHelper(Integer currPage, Integer pageSize);

    /**
     * 测试分页助手
     */
    public void testFindByPageHelper(Integer currPage, Integer pageSize);
}
