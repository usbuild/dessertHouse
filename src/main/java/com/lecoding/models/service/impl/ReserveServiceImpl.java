package com.lecoding.models.service.impl;

import com.lecoding.models.dao.IReserveDAO;
import com.lecoding.models.dao.IStoreDAO;
import com.lecoding.models.po.*;
import com.lecoding.models.service.IReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午9:23
 */
@Service
public class ReserveServiceImpl implements IReserveService {
    @Autowired
    IReserveDAO orderDAO;
    @Autowired
    IStoreDAO storeDAO;

    @Override
    public Reserve findById(int id) {
        return orderDAO.findById(id);
    }

    @Override
    public void addReserve(Map<String, String> map, Customer customer) throws Exception {

        List<ReserveGoods> list = new ArrayList<ReserveGoods>();
        Reserve reserve = new Reserve();
        reserve.setCustomer(customer);

        double total = 0;
        for (String key : map.keySet()) {
            Store store = storeDAO.findById(Integer.parseInt(key));
            int num = Integer.parseInt(map.get(key));
            if (store.getAmount() < num) throw new Exception("库存不足");
            store.setAmount(store.getAmount() - num);
            ReserveGoods reserveGoods = new ReserveGoods();
            reserveGoods.setAmount(num);
            reserveGoods.setStore(store);
            reserveGoods.setReserve(reserve);
            reserve.addReserveGoods(reserveGoods);
            total += num * store.getPrice();
            if (total > customer.getAmount()) throw new Exception("余额不足");
            storeDAO.update(store);
        }
        reserve.setTotal(total);
        orderDAO.save(reserve);
    }

    @Override
    public List<Reserve> allReserves(Customer customer) {
        return orderDAO.allReserves(customer);
    }


}
