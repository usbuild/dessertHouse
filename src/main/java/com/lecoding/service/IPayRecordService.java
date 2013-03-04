package com.lecoding.service;

import com.lecoding.models.Customer;
import com.lecoding.models.PayRecord;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-1 下午1:17
 */
public interface IPayRecordService {
    boolean insert(Customer customer, int amount);
    List<PayRecord> listRecord(Customer customer);
}
