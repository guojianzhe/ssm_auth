package com.itheima.dao;

import com.itheima.domain.Order;
import com.itheima.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderDao {


    //@Results 映射多列数据
    //@Result 映射单列数据
    @Select("select * from orders")
    @Results(@Result(property = "product",column = "productId",javaType = Product.class,
        one = @One(select="com.itheima.dao.ProductDao.findById")
    ))
    public List<Order> findAll();

    @Insert("insert into orders values(order_seq.nextval,#{orderNum},#{orderTime},#{peopleCount},#{orderDesc},#{payType},#{orderStatus},#{product.id})")
    public void save(Order order);
}
