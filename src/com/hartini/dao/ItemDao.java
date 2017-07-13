package com.hartini.dao;

import com.hartini.entity.Item;
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
public class ItemDao {
    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(Item item) {
        entityManager.persist(item);
    }

    public void update(Item item) {
        entityManager.merge(item);
    }

    public void delete(int id) {
        Item item = entityManager.find(Item.class, id);
        entityManager.remove(item);
    }

    public int count() {
        Query query = entityManager.createNamedQuery("countItem");
        return ((Number)query.getSingleResult()).intValue();
    }

    public Item selectById(int id) {
        Item item = entityManager.find(Item.class, id);
        return item;
    }

    public List<Item> selectInCart(List<Integer> id) {
        Query query = entityManager.createNamedQuery("selectInCart")
                .setParameter("list", id);
        List<Item> items = query.getResultList();
        return items;
    }

    public List<Item> selectByCategory(int id) {
        Query query = entityManager.createNamedQuery("selectByCategory")
                .setParameter("id", id);
        List<Item> items = query.getResultList();
        return items;
    }

    public List<Item> selectByCategoryLimit(int id, int start, int limit) {
        Query query = entityManager.createNamedQuery("selectByCategory")
                .setParameter("id", id)
                .setFirstResult(start)
                .setMaxResults(limit);
        List<Item> items = query.getResultList();
        return items;
    }

    public List<Item> selectByPage(int start, int limit) {
        Query query = entityManager.createNamedQuery("selectAllItem")
                .setFirstResult(start)
                .setMaxResults(limit);
        List<Item> items = query.getResultList();
        return items;
    }

    public List<Item> selectAll() {
        Query query = entityManager.createNamedQuery("selectAllItem");
        List<Item> items = query.getResultList();
        return items;
    }
}
