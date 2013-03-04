package com.lecoding.service;

import com.lecoding.models.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午7:33
 */

public interface ICustomerService {
    Customer getAnonymousCustomer();

    boolean add(Customer customer);

    boolean update(Customer customer);

    Customer findById(int id);

    boolean disable(Customer customer);

    Customer findByName(String name);

    public boolean pay(Customer customer, int money);
}
