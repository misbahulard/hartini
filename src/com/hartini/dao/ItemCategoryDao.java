package com.hartini.dao;

import com.hartini.entity.ItemCategory;
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
public class ItemCategoryDao {
    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(ItemCategory itemCategory) {
        entityManager.persist(itemCategory);
    }

    public void update(ItemCategory itemCategory) {
        entityManager.merge(itemCategory);
    }

    public void delete(int id) {
        ItemCategory itemCategory = entityManager.find(ItemCategory.class, id);
        entityManager.remove(itemCategory);
    }

    public int count() {
        Query query = entityManager.createNamedQuery("countItemCategory");
        return ((Number)query.getSingleResult()).intValue();
    }

    public ItemCategory selectById(int id) {
        ItemCategory itemCategory = entityManager.find(ItemCategory.class, id);
        return itemCategory;
    }

    public List<ItemCategory> selectByPage(int start, int limit) {
        Query query = entityManager.createNamedQuery("selectAllItemCategory")
                .setFirstResult(start)
                .setMaxResults(limit);
        List<ItemCategory> itemCategoryList = query.getResultList();
        return itemCategoryList;
    }

    public List<ItemCategory> selectAll() {
        Query query = entityManager.createNamedQuery("selectAllItemCategory");
        List<ItemCategory> itemCategorys = query.getResultList();
        return itemCategorys;
    }
}
