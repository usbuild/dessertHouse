package com.lecoding.models.pojo;

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

//    private int orderId;

//    @Column
//    @Basic
//    public int getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(int orderId) {
//        this.orderId = orderId;
//    }
//
//    private int goodsId;
//
//    @Column
//    @Basic
//    public int getGoodsId() {
//        return goodsId;
//    }
//
//    public void setGoodsId(int goodsId) {
//        this.goodsId = goodsId;
//    }

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

    private Goods goods;

    @OneToOne
    @JoinColumn(name = "goods_id")
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    private Reserve reserve;

    @ManyToOne
    @JoinColumn(name = "order_id")
    public Reserve getReserve() {
        return reserve;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }
}
