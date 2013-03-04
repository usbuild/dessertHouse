package com.lecoding.service.impl;

import com.lecoding.components.DessertException;
import com.lecoding.controllers.forms.GoodsForm;
import com.lecoding.dao.IGoodsDAO;
import com.lecoding.dao.IGoodsTypeDAO;
import com.lecoding.models.Goods;
import com.lecoding.models.GoodsType;
import com.lecoding.service.IGoodsService;
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
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    IGoodsDAO goodsDAO;
    @Autowired
    IGoodsTypeDAO goodsTypeDAO;

    @Override
    public List<Goods> allGoods() {
        return goodsDAO.findByCriteria();
    }

    @Override
    public List<GoodsType> allGoodsType() {
        return goodsTypeDAO.findByCriteria();
    }

    @Override
    public boolean addGoodsType(String name) {
        try {
            GoodsType type = new GoodsType();
            type.setName(name);
            goodsTypeDAO.save(type);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    @Override
    public Goods addGoods(GoodsForm goodsForm){
        Goods goods = new Goods();
        List<GoodsType> goodsTypes = goodsTypeDAO.findByCriteria(Restrictions.eq("name", goodsForm.getType()));
        if(goodsTypes.isEmpty()) throw new DessertException("Goods Type Not Found");
        GoodsType type = goodsTypes.get(0);
        goods.setGoodsType(type);
        goods.setName(goodsForm.getName());
        goods.setSid(goodsForm.getSid());
        goodsDAO.save(goods);
        return goods;
    }
}
