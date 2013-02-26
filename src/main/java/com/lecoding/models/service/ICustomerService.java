package com.lecoding.models.service;

import com.lecoding.models.po.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午7:33
 */

public interface ICustomerService {
    boolean add(Customer customer);
    Customer findById(int id);
    Customer findByName(String name);
}
