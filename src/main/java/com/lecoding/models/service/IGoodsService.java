package com.lecoding.models.service;

import com.lecoding.models.po.Goods;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-27 下午9:44
 */
public interface IGoodsService {
    List<Goods> searchGoods(String name);
}
