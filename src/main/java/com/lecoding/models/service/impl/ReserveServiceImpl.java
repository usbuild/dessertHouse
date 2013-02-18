package com.lecoding.models.service.impl;

import com.lecoding.models.dao.IReserveDAO;
import com.lecoding.models.pojo.Goods;
import com.lecoding.models.pojo.Reserve;
import com.lecoding.models.service.IReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public Reserve findById(int id) {
        return orderDAO.findById(id);
    }

    @Override
    @Transactional
    public Map<Goods, Integer> getGoodsList(int orderId) {
        return orderDAO.getGoodsList(orderId);
    }
}
