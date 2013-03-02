package com.lecoding.models.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午4:21
 */
@javax.persistence.Table(name = "reserve_goods", schema = "", catalog = "dessert")
@Entity
public class ReserveGoods implements Serializable {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int amount;

    @javax.persistence.Column(name = "amount", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReserveGoods that = (ReserveGoods) o;

        if (amount != that.amount) return false;
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + amount;
        return result;
    }


    private Store store;

    @ManyToOne
    @JoinColumn(name = "store_id")
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    private Reserve reserve;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reserve_id", referencedColumnName = "id")
    public Reserve getReserve() {
        return reserve;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }
}
