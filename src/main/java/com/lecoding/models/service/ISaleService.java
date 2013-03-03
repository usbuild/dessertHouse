package com.lecoding.models.service;

import com.lecoding.controllers.forms.BuyForm;
import com.lecoding.models.po.Customer;
import com.lecoding.models.po.Sale;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午9:23
 */
public interface ISaleService {
    Sale findById(int id);

    void addReserve(Map<String, String> map, Customer customer) throws Exception;

    void addSale(BuyForm buyForm) throws Exception;

    List<Sale> allReserves(Customer customer);

    List<Sale> allSales(Customer customer);
}
