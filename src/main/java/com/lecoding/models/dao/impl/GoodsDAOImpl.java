package com.lecoding.models.dao.impl;

import com.lecoding.models.dao.IGoodsDAO;
import com.lecoding.models.po.Goods;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午4:27
 */
@Repository
public class GoodsDAOImpl extends BaseDAOSupport<Goods> implements IGoodsDAO {
    @SuppressWarnings("unchecked")
    @Override
    public Map searchGoods(String key, int page, int pageSize) {

        Map<String, Object> map = new HashMap<String, Object>();

        long count = (Long) getSession().createQuery("SELECT count(*) FROM Goods WHERE name LIKE :key").setString("key", "%" + key + "%").uniqueResult();
        map.put("pages", (int) Math.ceil(count * 1.0 / pageSize));
        map.put("page", page);
        map.put("pageSize", pageSize);
        Query query = getSession().createQuery("FROM Goods WHERE name LIKE :key").setString("key", "%" + key + "%")
                .setFirstResult(page * pageSize)
                .setMaxResults(pageSize);
        map.put("data", query.list());
        return map;
    }
}
