package com.lecoding.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-8 下午3:12
 */
@Entity
public class Discount implements Serializable {
    private int id;

    @javax.persistence.Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int level;

    @javax.persistence.Column(name = "level")
    @Basic
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private double discount;

    @javax.persistence.Column(name = "discount")
    @Basic
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount1 = (Discount) o;

        if (Double.compare(discount1.discount, discount) != 0) return false;
        if (id != discount1.id) return false;
        if (level != discount1.level) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + level;
        temp = discount != +0.0d ? Double.doubleToLongBits(discount) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
