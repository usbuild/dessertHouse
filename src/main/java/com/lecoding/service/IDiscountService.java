package com.lecoding.service;

import com.lecoding.models.Discount;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-8 下午3:23
 */
public interface IDiscountService {
    List<Discount> allDiscount();
    void setDiscount(int id, double discount);
}
