package com.lecoding.models.service.impl;

import com.lecoding.models.dao.IGoodsDAO;
import com.lecoding.models.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-27 下午9:45
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    IGoodsDAO goodsDAO;

    @Override
    public Map searchGoods(String name, int page) {
        return goodsDAO.searchGoods(name, page, 10);
    }

}
