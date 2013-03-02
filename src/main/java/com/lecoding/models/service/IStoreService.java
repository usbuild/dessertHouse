package com.lecoding.models.service;

import com.lecoding.models.po.Store;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-2 上午10:59
 */
public interface IStoreService {
    List<Store> searchStore(int shopId, String key, Date date);
}
