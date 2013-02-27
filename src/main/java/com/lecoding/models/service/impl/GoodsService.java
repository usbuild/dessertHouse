package com.lecoding.models.service.impl;

import com.lecoding.models.dao.IGoodsDAO;
import com.lecoding.models.po.Goods;
import com.lecoding.models.service.IGoodsService;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-27 下午9:45
 */
@Service
public class GoodsService implements IGoodsService {

    @Autowired
    IGoodsDAO goodsDAO;

    @Override
    public List<Goods> searchGoods(String name) {
        return goodsDAO.findByCriteria(Restrictions.like("name", "%" + name + "%"));
    }
}
