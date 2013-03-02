package com.lecoding.models.service.impl;

import com.lecoding.models.dao.ICustomerDAO;
import com.lecoding.models.dao.ISaleDAO;
import com.lecoding.models.dao.IStoreDAO;
import com.lecoding.models.po.Customer;
import com.lecoding.models.po.Sale;
import com.lecoding.models.po.SaleGoods;
import com.lecoding.models.po.Store;
import com.lecoding.models.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午9:23
 */
@Service
public class SaleServiceImpl implements ISaleService {

    @Autowired
    ICustomerDAO customerDAO;

    @Autowired
    ISaleDAO orderDAO;
    @Autowired
    IStoreDAO storeDAO;

    @Override
    public Sale findById(int id) {
        return orderDAO.findById(id);
    }

    @Override
    public void addReserve(Map<String, String> map, Customer customer) throws Exception {

        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setReserve(1);

        double total = 0;
        for (String key : map.keySet()) {
            Store store = storeDAO.findById(Integer.parseInt(key));
            int num = Integer.parseInt(map.get(key));
            if (store.getAmount() < num) throw new Exception("库存不足");
            store.setAmount(store.getAmount() - num);
            SaleGoods saleGoods = new SaleGoods();
            saleGoods.setAmount(num);
            saleGoods.setStore(store);
            saleGoods.setSale(sale);

            sale.addSaleGoods(saleGoods);
            total += num * store.getPrice();
            if (total > customer.getAmount()) throw new Exception("余额不足");
            storeDAO.update(store);
        }
        customer.setAmount(customer.getAmount() - (int) total);
        customerDAO.update(customer);
        sale.setTotal(total);
        orderDAO.save(sale);
    }

    @Override
    public List<Sale> allReserves(Customer customer) {
        return orderDAO.allReserves(customer);
    }

    @Override
    public List<Sale> allSales(Customer customer) {
        return orderDAO.allSales(customer);
    }

}
