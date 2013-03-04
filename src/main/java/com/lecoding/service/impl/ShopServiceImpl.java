package com.lecoding.service.impl;

import com.lecoding.dao.IShopDAO;
import com.lecoding.models.Shop;
import com.lecoding.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-2 上午10:28
 */
@Service
public class ShopServiceImpl implements IShopService {

    @Autowired
    IShopDAO shopDAO;

    @Override
    public List<Shop> allShops() {
        return shopDAO.findByCriteria();
    }
}
