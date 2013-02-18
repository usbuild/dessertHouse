package com.lecoding.models.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午3:44
 */
@Entity
public class Goods implements Serializable {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String sid;

    @javax.persistence.Column(name = "sid", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    private String name;

    @javax.persistence.Column(name = "name", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private double price;

    @javax.persistence.Column(name = "price", nullable = false, insertable = true, updatable = true, length = 5, precision = 0)
    @Basic
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    private GoodsType goodsType;

    @OneToOne
    @JoinColumn(name = "type")
    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goods goods = (Goods) o;

        if (amount != goods.amount) return false;
        if (id != goods.id) return false;
        if (Double.compare(goods.price, price) != 0) return false;
        if (name != null ? !name.equals(goods.name) : goods.name != null) return false;
        if (sid != null ? !sid.equals(goods.sid) : goods.sid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (sid != null ? sid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = price != +0.0d ? Double.doubleToLongBits(price) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + amount;
        return result;
    }
}
