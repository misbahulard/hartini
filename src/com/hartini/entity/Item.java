package com.hartini.entity;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by misbahul on 14/06/17.
 */
@Entity
@Table(name = "item")
@NamedQueries({
        @NamedQuery(name = "selectAllItem", query = "select i from Item i"),
        @NamedQuery(name = "selectByCategory", query = "select i from Item i where categoryId = :id"),
        @NamedQuery(name = "countItem", query = "select count (i) from Item i"),
        @NamedQuery(name = "selectInCart", query = "select i from Item i where i.id in :list"),
})
public class Item {
    private int id;
    private String name;
    private int price;
    private int categoryId;
    private String image;
    private ItemCategory itemCategoryByCategoryId;
    private Collection<OrderDetail> orderDetailsById;
    private MultipartFile file;

    public Item() {
        this.image = "default.jpg";
    }

    @Transient
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (price != item.price) return false;
        if (categoryId != item.categoryId) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (image != null ? !image.equals(item.image) : item.image != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + categoryId;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public ItemCategory getItemCategoryByCategoryId() {
        return itemCategoryByCategoryId;
    }

    public void setItemCategoryByCategoryId(ItemCategory itemCategoryByCategoryId) {
        this.itemCategoryByCategoryId = itemCategoryByCategoryId;
    }

    @OneToMany(mappedBy = "itemByItemId")
    public Collection<OrderDetail> getOrderDetailsById() {
        return orderDetailsById;
    }

    public void setOrderDetailsById(Collection<OrderDetail> orderDetailsById) {
        this.orderDetailsById = orderDetailsById;
    }
}
