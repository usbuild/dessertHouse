package com.lecoding.dao;

import com.lecoding.models.Goods;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午4:25
 */
public interface IGoodsDAO extends IBaseDAO<Goods> {
    Map searchGoods(String key, int page, int pageSize);
}
