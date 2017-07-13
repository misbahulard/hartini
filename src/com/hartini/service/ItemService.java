package com.hartini.service;

import com.hartini.dao.ItemDao;
import com.hartini.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by misbahul on 15/06/17.
 */
@Service
public class ItemService {
    @Autowired
    private ItemDao itemDao;

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void addItem(Item item) {
        getItemDao().insert(item);
    }

    public void updateItem(Item item) {
        getItemDao().update(item);
    }

    public void removeItem(int id) {
        getItemDao().delete(id);
    }

    public int countItem() {
        return getItemDao().count();
    }

    public Item fetchItemById(int id) {
        return getItemDao().selectById(id);
    }

    public List<Item> fetchItemInCart(List<Integer> id) {
        return getItemDao().selectInCart(id);
    }

    public List<Item> fetchItemByCategory(int id) {
        return getItemDao().selectByCategory(id);
    }

    public List<Item> fetchItemByCategoryLimit(int id, int start, int limit) {
        return getItemDao().selectByCategoryLimit(id, start, limit);
    }

    public List<Item> fetchItemByPage(int start, int limit) {
        return getItemDao().selectByPage(start, limit);
    }

    public List<Item> fetchAllItem() {
        return getItemDao().selectAll();
    }
}
