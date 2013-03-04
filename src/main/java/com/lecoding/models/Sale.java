package com.lecoding.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午3:44
 */
@Entity
@Table(name = "sale")
public class Sale implements Serializable {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    private int isReserve = 0;

    @Column(name = "is_reserve")
    @Basic
    public int getReserve() {
        return isReserve;
    }

    public void setReserve(int reserve) {
        isReserve = reserve;
    }

    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name = "total")
    @Basic
    private double total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sale sale = (Sale) o;

        if (id != sale.id) return false;
        if (createTime != null ? !createTime.equals(sale.createTime) : sale.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    private List<SaleGoods> saleGoods;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sale", fetch = FetchType.EAGER)
    public List<SaleGoods> getSaleGoods() {
        return saleGoods;
    }

    public void setSaleGoods(List<SaleGoods> saleGoods) {
        this.saleGoods = saleGoods;
    }

    public void addSaleGoods(SaleGoods saleGoods) {
        if (this.saleGoods == null) this.saleGoods = new ArrayList<SaleGoods>();
        saleGoods.setSale(this);
        this.saleGoods.add(saleGoods);
    }
}
