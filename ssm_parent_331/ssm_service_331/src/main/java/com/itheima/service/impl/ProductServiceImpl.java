package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.ProductDao;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public List<Product> findAll() {

        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public Product findById(Integer id) {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void deleteOne(Integer id) {

        productDao.deleteOne(id);
    }

    @Override
    public void delMany(Integer[] ids) {

        if(ids!=null){
            for (Integer id : ids) {
                productDao.deleteOne(id);
            }
        }

    }

    @Override
    public PageBean<Product> findByPage(Integer currPage, Integer pageSize) {
        PageBean<Product> pageBean = new PageBean<>();
        //封装pegebean
        //private Integer currPage;
        //每页条数  --页面传参
        //private Integer pageSize;
        //总条数   --数据库查询
        //private Long totalCount;
        //总页数   --计算  总条数/每页的条数    math.ceil(totalCount*1.0/pageSize)
        //private Integer totalPage;
        //当前页数据  --数据库查询
        //private List<T> list;

        //当前页面
        pageBean.setCurrPage(currPage);
        //每页条数
        pageBean.setPageSize(pageSize);
        //去数据库查询总条数
        Long totalCount =  productDao.findTotalCount();
        //总条数
        pageBean.setTotalCount(totalCount);
        //总页数
        pageBean.setTotalPage((int)Math.ceil(totalCount*1.0/pageSize));


        /**
         * 每页显示5条  pageSize
         * 第一页   1     5
         * 第二页   6    10
         * 第三页   11   15
         * 第四页   16   20
         *
         * currPage   currPage*pageSize-(pageSize-1)        currPage*pageSize
         *
         *
         */
        Integer startIndex = pageSize*(currPage-1)+1;
        Integer endIndex   = currPage*pageSize;

        //从数据库中将当前页面查询出来
        List<Product> productList = productDao.findByPage(startIndex,endIndex);
        //当前页数据
        pageBean.setList(productList);

        return pageBean;
    }

    @Override
    public PageInfo<Product> findByPageHelper(Integer currPage, Integer pageSize) {
        //指定分页助手参数
        PageHelper.startPage(currPage,pageSize);
        List<Product> productList = productDao.findAll();
        PageInfo<Product> pageInfo = new PageInfo<>(productList,3);

        return pageInfo;
    }

    @Override
    public void testFindByPageHelper(Integer currPage, Integer pageSize) {
        //为分页助手查询参数
        PageHelper.startPage(currPage,pageSize);
        List<Product> productList = productDao.findAll();
        PageInfo<Product> pageInfo = new PageInfo<>(productList);


    }


}
