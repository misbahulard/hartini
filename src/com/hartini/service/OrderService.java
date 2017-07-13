package com.hartini.service;

import com.hartini.dao.OrderDao;
import com.hartini.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by misbahul on 15/06/17.
 */
@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public boolean addTransaction(Order order, int[] itemId) {
        return getOrderDao().newTransaction(order, itemId);
    }

    public void addOrder(Order order) {
        getOrderDao().insert(order);
    }

    public void updateOrder(Order order) {
        getOrderDao().update(order);
    }

    public void removeOrder(int id) {
        getOrderDao().delete(id);
    }

    public int countOrder() {
        return getOrderDao().count();
    }

    public Order fetchOrderById(int id) {
        return getOrderDao().selectById(id);
    }

    public List<Order> fetchAllOrder() {
        return getOrderDao().selectAll();
    }
}
