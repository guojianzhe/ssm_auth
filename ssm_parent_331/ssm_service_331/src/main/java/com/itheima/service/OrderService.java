package com.itheima.service;


import com.itheima.domain.Order;

import java.util.List;

public interface OrderService {

    public List<Order> findAll();

    public void save(Order order);
}
