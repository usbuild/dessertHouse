package com.lecoding.service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-5 下午3:55
 */
public interface IManagerService {
    Map<String, Integer> groupCustomerByStatus();

    Map<String, Integer> groupCustomerByArea();

    Map<String, Integer> groupCustomerByAge();

    Map<String, Integer> groupCustomerByGender();

    Map<String, Integer> saleAmountByShop(int shop_id);

    Map<String, Integer> reserveAmountByShop(int shop_id);

    Map<String, Integer> top10();
}
