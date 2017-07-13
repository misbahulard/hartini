package com.hartini.service;

import com.hartini.dao.OrderDetailDao;
import com.hartini.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by misbahul on 15/06/17.
 */
@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailDao orderDetailDao;

    public OrderDetailDao getOrderDetailDao() {
        return orderDetailDao;
    }

    public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
        this.orderDetailDao = orderDetailDao;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        getOrderDetailDao().insert(orderDetail);
    }

    public void updateOrderDetail(OrderDetail orderDetail) {
        getOrderDetailDao().update(orderDetail);
    }

    public void removeOrderDetail(int id) {
        getOrderDetailDao().delete(id);
    }

    public OrderDetail fetchOrderDetailById(int id) {
        return getOrderDetailDao().selectById(id);
    }

    public List<OrderDetail> fetchOrderDetailByOrderId(int id) {
        return getOrderDetailDao().selectByOrderId(id);
    }

    public List<OrderDetail> fetchAllOrderDetail() {
        return getOrderDetailDao().selectAll();
    }
}
