package com.lecoding.models.po;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-1 下午1:20
 */
@javax.persistence.Table(name = "pay_record", schema = "", catalog = "dessert")
@Entity
public class PayRecord implements Serializable {
    private int id;

    @javax.persistence.Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    private int amount;

    @javax.persistence.Column(name = "amount")
    @Basic
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private Timestamp createTime;

    @javax.persistence.Column(name = "create_time")
    @Basic
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PayRecord payRecord = (PayRecord) o;

        if (amount != payRecord.amount) return false;
        if (id != payRecord.id) return false;
        if (createTime != null ? !createTime.equals(payRecord.createTime) : payRecord.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + amount;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
