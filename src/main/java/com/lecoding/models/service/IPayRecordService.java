package com.lecoding.models.service;

import com.lecoding.models.po.Customer;
import com.lecoding.models.po.PayRecord;

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
