package com.lecoding.models.service;

import com.lecoding.models.po.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午7:33
 */

public interface ICustomerService {
    boolean add(Customer customer);

    boolean update(Customer customer);

    Customer findById(int id);

    boolean disable(Customer customer);

    Customer findByName(String name);

    public boolean pay(Customer customer, int money);
}
