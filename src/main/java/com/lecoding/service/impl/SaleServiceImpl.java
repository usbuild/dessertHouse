package com.lecoding.service.impl;

import com.lecoding.components.DessertException;
import com.lecoding.controllers.forms.BuyForm;
import com.lecoding.dao.ISaleDAO;
import com.lecoding.dao.IStoreDAO;
import com.lecoding.models.Customer;
import com.lecoding.models.Sale;
import com.lecoding.models.SaleGoods;
import com.lecoding.models.Store;
import com.lecoding.service.ICustomerService;
import com.lecoding.service.ISaleService;
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
    ICustomerService customerService;

    @Autowired
    ISaleDAO saleDAO;
    @Autowired
    IStoreDAO storeDAO;

    @Override
    public Sale findById(int id) {
        return saleDAO.findById(id);
    }

    @Override
    public void addReserve(Map<String, String> map, Customer customer){

        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setReserve(1);

        double total = 0;
        for (String key : map.keySet()) {
            Store store = storeDAO.findById(Integer.parseInt(key));
            int num = Integer.parseInt(map.get(key));
            if (store.getAmount() < num) throw new DessertException("库存不足");
            store.setAmount(store.getAmount() - num);
            SaleGoods saleGoods = new SaleGoods();
            saleGoods.setAmount(num);
            saleGoods.setStore(store);
            saleGoods.setSale(sale);

            sale.addSaleGoods(saleGoods);
            total += num * store.getPrice();
            if (total > customer.getAmount()) throw new DessertException("余额不足");
            storeDAO.update(store);
        }
        customer.setAmount(customer.getAmount() - (int) total);
        customerService.update(customer);
        sale.setTotal(total);
        saleDAO.save(sale);
    }

    @Override
    public void addSale(BuyForm buyForm) throws Exception {
        Customer customer = customerService.findByName(buyForm.getCustomer());
        if (customer == null) {
            customer = customerService.getAnonymousCustomer();
        }

        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setReserve(0);

        double total = 0 - buyForm.getPay();

        for (String key : buyForm.getGoods().keySet()) {
            Store store = storeDAO.findById(Integer.parseInt(key));
            int num = Integer.parseInt(buyForm.getGoods().get(key));

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
        customerService.update(customer);
        sale.setTotal(total + buyForm.getPay());
        saleDAO.save(sale);
    }

    @Override
    public List<Sale> allReserves(Customer customer) {
        return saleDAO.allReserves(customer);
    }

    @Override
    public List<Sale> allSales(Customer customer) {
        return saleDAO.allSales(customer);
    }

}
