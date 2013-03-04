package com.lecoding.dao;

import com.lecoding.models.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-上午9:44
 */
public interface ICustomerDAO extends IBaseDAO<Customer> {
    Customer findByName(String name);
}
