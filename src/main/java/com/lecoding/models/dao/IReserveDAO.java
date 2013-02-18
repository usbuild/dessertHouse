package com.lecoding.models.dao;

import com.lecoding.models.pojo.Goods;
import com.lecoding.models.pojo.Reserve;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午4:31
 */
public interface IReserveDAO extends IBaseDAO<Reserve> {
    Map<Goods, Integer> getGoodsList(int orderId);
}
