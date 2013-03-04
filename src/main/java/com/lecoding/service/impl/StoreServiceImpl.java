package com.lecoding.service.impl;

import com.lecoding.components.DessertException;
import com.lecoding.controllers.forms.StoreForm;
import com.lecoding.dao.IGoodsDAO;
import com.lecoding.dao.IGoodsTypeDAO;
import com.lecoding.dao.IStoreDAO;
import com.lecoding.models.GoodsType;
import com.lecoding.models.Store;
import com.lecoding.models.User;
import com.lecoding.service.IStoreService;
import org.hibernate.criterion.Restrictions;
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

    @Autowired
    IGoodsDAO goodsDAO;

    @Autowired
    IGoodsTypeDAO goodsTypeDAO;

    @SuppressWarnings("unchecked")
    @Override
    public List<Store> searchStore(int shopId, String key, Date date) {
        return storeDAO.searchGoods(shopId, key, date);
    }

    @Override
    public void addStore(StoreForm form) throws DessertException {
        List<GoodsType> goodsTypes = goodsTypeDAO.findByCriteria(Restrictions.eq("name", form.getGoodsType()));
    }

    @Override
    public boolean delStore(int id, User user) {
        Store store = storeDAO.findById(id);
        if (store.getShop().getId() != user.getShop().getId()) {
            return false;
        } else {
            try {
                storeDAO.delete(store);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }
}
