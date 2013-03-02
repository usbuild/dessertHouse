package com.lecoding.models.service.impl;

import com.lecoding.models.dao.IShopDAO;
import com.lecoding.models.po.Shop;
import com.lecoding.models.service.IShopService;
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
