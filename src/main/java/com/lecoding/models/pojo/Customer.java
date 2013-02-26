package com.lecoding.models.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-上午9:25
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    public static enum StatusType {
        nouse, active, cancel
    }

    private StatusType status = StatusType.nouse;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Basic
    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    private int id;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    @Column(name = "name", nullable = false)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String password;

    @Column(name = "password", nullable = false)
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String salt;

    @Column(name = "salt", nullable = false)
    @Basic
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    private int amount;

    @Column(name = "amount", nullable = false)
    @Basic
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private GenderType gender = GenderType.male;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Basic
    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    private int age;

    @Column(name = "age", nullable = false)
    @Basic
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private Area area;

    @OneToOne
    @JoinColumn(name = "area_id")
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (age != customer.age) return false;
        if (amount != customer.amount) return false;
        if (id != customer.id) return false;
        if (gender != null ? !gender.equals(customer.gender) : customer.gender != null) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (password != null ? !password.equals(customer.password) : customer.password != null) return false;
        if (salt != null ? !salt.equals(customer.salt) : customer.salt != null) return false;
        if (status != null ? !status.equals(customer.status) : customer.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + amount;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }
}
