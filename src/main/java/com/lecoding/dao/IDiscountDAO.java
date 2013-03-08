package com.lecoding.dao;

import com.lecoding.models.Discount;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-8 下午3:13
 */
public interface IDiscountDAO extends IBaseDAO<Discount> {
    List<Discount> findAll();
}
