package com.lecoding.service;

import com.lecoding.components.DessertException;
import com.lecoding.controllers.forms.StoreForm;
import com.lecoding.models.Store;
import com.lecoding.models.User;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-2 上午10:59
 */
public interface IStoreService {
    List<Store> searchStore(int shopId, String key, Date date);
    void addStore(StoreForm form) throws DessertException;
    boolean delStore(int id, User user);
}
