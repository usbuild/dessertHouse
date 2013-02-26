package com.lecoding.models.service;

import com.lecoding.models.po.Goods;
import com.lecoding.models.po.Reserve;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午9:23
 */
public interface IReserveService {
    Reserve findById(int id);

    Map<Goods, Integer> getGoodsList(int orderId);
}
