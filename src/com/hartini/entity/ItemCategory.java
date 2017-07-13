package com.hartini.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by misbahul on 14/06/17.
 */
@Entity
@Table(name = "item_category", schema = "hartini", catalog = "")
@NamedQueries({
        @NamedQuery(name = "selectAllItemCategory", query = "select ic from ItemCategory ic"),
        @NamedQuery(name = "countItemCategory", query = "select count (ic) from ItemCategory ic")
})
public class ItemCategory {
    private int id;
    private String name;
    private Collection<Item> itemsById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemCategory that = (ItemCategory) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "itemCategoryByCategoryId")
    public Collection<Item> getItemsById() {
        return itemsById;
    }

    public void setItemsById(Collection<Item> itemsById) {
        this.itemsById = itemsById;
    }
}
