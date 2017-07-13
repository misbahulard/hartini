package com.hartini.dao;

import com.hartini.entity.OrderDetail;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by misbahul on 15/06/17.
 */
@Repository
@Transactional
public class OrderDetailDao {
    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(OrderDetail orderDetail) {
        entityManager.persist(orderDetail);
    }

    public void update(OrderDetail orderDetail) {
        entityManager.merge(orderDetail);
    }

    public void delete(int id) {
        OrderDetail orderDetail = entityManager.find(OrderDetail.class, id);
        entityManager.remove(orderDetail);
    }

    public OrderDetail selectById(int id) {
        OrderDetail orderDetail = entityManager.find(OrderDetail.class, id);
        return orderDetail;
    }

    public List<OrderDetail> selectByOrderId(int id) {
        Query query = entityManager.createNamedQuery("selectOrderDetailByOrderId")
                .setParameter("oid", id);
        List<OrderDetail> orderDetails = query.getResultList();
        return orderDetails;
    }

    public List<OrderDetail> selectAll() {
        Query query = entityManager.createNamedQuery("selectAllOrderDetail");
        List<OrderDetail> orderDetails = query.getResultList();
        return orderDetails;
    }
}
