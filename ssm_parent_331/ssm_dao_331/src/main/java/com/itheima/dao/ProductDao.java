package com.itheima.dao;


import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductDao {

    @Select("select * from product")
    public List<Product> findAll();

//    @Insert("insert into product values (product_seq.nextval,#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    @Insert("insert into product values (#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    @SelectKey(keyProperty = "id",keyColumn = "id",before = true,resultType = Integer.class,statement = "select product_seq.nextval from dual")
    public void save(Product product);


    @Select("select * from product where id = #{id}")
    public Product findById(Integer id);

    @Select("update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id=#{id}")
    void update(Product product);
}
