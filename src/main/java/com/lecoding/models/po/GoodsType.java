package com.lecoding.models.po;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-4-上午11:57
 */
@javax.persistence.Table(name = "goods_type", schema = "", catalog = "dessert")
@Entity
public class GoodsType {
    public GoodsType() {
    }

    public GoodsType(int id) {
        this.id = id;
    }

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

    private String name;

    @javax.persistence.Column(name = "name", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
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

        GoodsType goodsType = (GoodsType) o;

        if (id != goodsType.id) return false;
        if (name != null ? !name.equals(goodsType.name) : goodsType.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
