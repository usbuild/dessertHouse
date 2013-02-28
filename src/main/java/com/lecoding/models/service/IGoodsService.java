package com.lecoding.models.service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-27 下午9:44
 */
public interface IGoodsService {
    Map searchGoods(String name, int page);
}
