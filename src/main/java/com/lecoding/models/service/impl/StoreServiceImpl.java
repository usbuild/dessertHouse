package com.lecoding.models.service.impl;

import com.lecoding.models.dao.IStoreDAO;
import com.lecoding.models.po.Store;
import com.lecoding.models.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-2 上午10:59
 */
@Service
public class StoreServiceImpl implements IStoreService {

    @Autowired
    IStoreDAO storeDAO;

    @SuppressWarnings("unchecked")
    @Override
    public List<Store> searchStore(int shopId, String key, Date date) {
        return storeDAO.searchGoods(shopId, key, date);
    }
}
