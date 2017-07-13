package com.hartini.service;

import com.hartini.dao.ItemCategoryDao;
import com.hartini.entity.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by misbahul on 15/06/17.
 */
@Service
public class ItemCategoryService {
    @Autowired
    private ItemCategoryDao itemCategoryDao;

    public ItemCategoryDao getItemCategoryDao() {
        return itemCategoryDao;
    }

    public void setItemCategoryDao(ItemCategoryDao itemCategoryDao) {
        this.itemCategoryDao = itemCategoryDao;
    }

    public void addItemCategory(ItemCategory itemCategory) {
        getItemCategoryDao().insert(itemCategory);
    }

    public void updateItemCategory(ItemCategory itemCategory) {
        getItemCategoryDao().update(itemCategory);
    }

    public void removeItemCategory(int id) {
        getItemCategoryDao().delete(id);
    }

    public int countItem() {
        return getItemCategoryDao().count();
    }

    public ItemCategory fetchItemCategoryById(int id) {
        return getItemCategoryDao().selectById(id);
    }

    public List<ItemCategory> fetchItemCategoryByPage(int start, int limit) {
        return getItemCategoryDao().selectByPage(start, limit);
    }

    public List<ItemCategory> fetchAllItemCategory() {
        return getItemCategoryDao().selectAll();
    }
}
