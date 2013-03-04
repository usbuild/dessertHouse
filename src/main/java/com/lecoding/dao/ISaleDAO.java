package com.lecoding.dao;

import com.lecoding.models.Customer;
import com.lecoding.models.Sale;

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
