package com.lecoding.service;

import com.lecoding.models.Shop;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-2 上午10:27
 */
public interface IShopService {
    List<Shop> allShops();
    void insert(String name);
    void deleteById(int id);
}
