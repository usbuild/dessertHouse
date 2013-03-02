package com.lecoding.models.service;

import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-27 下午9:44
 */
public interface IGoodsService {
    Map searchGoods(int shop_id, Date date, String key, int page);
}
