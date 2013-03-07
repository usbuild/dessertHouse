package com.lecoding.service;

import com.lecoding.controllers.forms.GoodsForm;
import com.lecoding.models.Goods;
import com.lecoding.models.GoodsType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-27 下午9:44
 */
public interface IGoodsService {
    Goods findById( int id);
    Goods updateGoods(int id, GoodsForm goodsForm);
    List<Goods> allGoods();

    List<GoodsType> allGoodsType();

    boolean addGoodsType(String name);

    Goods addGoods(GoodsForm goodsForm);
}
