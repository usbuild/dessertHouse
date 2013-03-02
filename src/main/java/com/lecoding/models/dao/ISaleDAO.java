package com.lecoding.models.dao;

import com.lecoding.models.po.Customer;
import com.lecoding.models.po.Sale;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午4:31
 */
public interface ISaleDAO extends IBaseDAO<Sale> {
    List<Sale> allReserves(Customer customer);
    List<Sale> allSales(Customer customer);
}
