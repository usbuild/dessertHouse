package com.lecoding.models.service;

import com.lecoding.models.po.Customer;
import com.lecoding.models.po.Reserve;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午9:23
 */
public interface IReserveService {
    Reserve findById(int id);

    void addReserve(Map<String, String> map, Customer customer) throws Exception;

    List<Reserve> allReserves(Customer customer);
}
