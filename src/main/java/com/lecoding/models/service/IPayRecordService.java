package com.lecoding.models.service;

import com.lecoding.models.po.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-1 下午1:17
 */
public interface IPayRecordService {
    boolean insert(Customer customer, int amount);
}
