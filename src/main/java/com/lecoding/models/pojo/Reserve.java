package com.lecoding.models.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午3:44
 */
@Entity
@Table(name = "reserve")
public class Reserve implements Serializable {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Timestamp createTime = new Timestamp(System.currentTimeMillis());

    @javax.persistence.Column(name = "create_time", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reserve reserve = (Reserve) o;

        if (id != reserve.id) return false;
        if (createTime != null ? !createTime.equals(reserve.createTime) : reserve.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    private List<ReserveGoods> reserveGoods;

    @OneToMany(mappedBy = "reserve")
    public List<ReserveGoods> getReserveGoods() {
        return reserveGoods;
    }

    public void setReserveGoods(List<ReserveGoods> reserveGoods) {
        this.reserveGoods = reserveGoods;
    }
}
