package com.lecoding.controllers.forms;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-3 下午2:22
 */
public class BuyForm {
    private String customer;
    private Map<String, String> goods;
    private int pay;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Map<String, String> getGoods() {
        return goods;
    }

    public void setGoods(Map<String, String> goods) {
        this.goods = goods;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }
}
