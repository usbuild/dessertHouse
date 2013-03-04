package com.lecoding.dao;

import com.lecoding.models.Store;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-2 上午10:48
 */
public interface IStoreDAO extends IBaseDAO<Store> {
    List searchGoods(int storeId, String name, Date date);
}
