package com.hartini.dao;

import com.hartini.entity.Order;
import com.hartini.entity.OrderDetail;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by misbahul on 15/06/17.
 */
@Repository
@Transactional
public class OrderDao {
    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean newTransaction(Order order, int[] orderId) {
        Order orderTemp = new Order();
        try {
            entityManager.persist(order);
            Query query = entityManager.createNamedQuery("selectLatestOrder");
            List<Order> orderList = query.getResultList();
            Order o = orderList.get(0);
            orderTemp = o;
//            Order o = (Order)query.getSingleResult();

            for (int i = 0; i < orderId.length; i++) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(o.getId());
                orderDetail.setItemId(orderId[i]);
                entityManager.persist(orderDetail);
            }
        } catch (NoResultException e) {
            entityManager.remove(orderTemp);
            e.printStackTrace();
            return false;
        } catch (Exception ex) {
            entityManager.remove(orderTemp);
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public void insert(Order order) {
        entityManager.persist(order);
    }

    public void update(Order order) {
        entityManager.merge(order);
    }

    public void delete(int id) {
        Order order = entityManager.find(Order.class, id);
        entityManager.remove(order);
    }

    public int count() {
        Query query = entityManager.createNamedQuery("countOrder");
        return ((Number)query.getSingleResult()).intValue();
    }

    public Order selectById(int id) {
        Order order = entityManager.find(Order.class, id);
        return order;
    }

    public List<Order> selectAll() {
        Query query = entityManager.createNamedQuery("selectAllOrder");
        List<Order> orders = query.getResultList();
        return orders;
    }
}
