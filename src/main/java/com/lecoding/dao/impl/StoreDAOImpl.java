package com.lecoding.dao.impl;

import com.lecoding.dao.IStoreDAO;
import com.lecoding.models.Store;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-2 上午10:48
 */
@Repository
public class StoreDAOImpl extends BaseDAOSupport<Store> implements IStoreDAO {
    @SuppressWarnings("unchecked")
    @Override
    public List<Store> searchGoods(int shopId, String name, Date date) {
        java.sql.Date d = new java.sql.Date(date.getTime());
        Query query = getSession().createQuery(
                "SELECT s FROM Store AS s LEFT JOIN s.goods AS g LEFT JOIN s.shop AS h WHERE h.id = :shopId AND s.saleTime = :time AND s.amount > 0 AND g.name LIKE :name"
        ).setInteger("shopId", shopId).setDate("time", d).setString("name", "%" + name + "%");
        return query.list();
    }
}
