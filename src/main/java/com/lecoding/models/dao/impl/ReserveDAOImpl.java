package com.lecoding.models.dao.impl;

import com.lecoding.models.dao.IReserveDAO;
import com.lecoding.models.po.Goods;
import com.lecoding.models.po.GoodsType;
import com.lecoding.models.po.Reserve;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午4:34
 */
@Repository
public class ReserveDAOImpl extends BaseDAOSupport<Reserve> implements IReserveDAO {
    @Override
    public Map<Goods, Integer> getGoodsList(int orderId) {
        String hql = "select rg.amount,g.id,g.sid,g.name,g.amount,g.price,g.goodsType " +
                "from Reserve as r " +
                "left join r.shop as s " +
                "left join r.reserveGoods as rg  " +
                "left join rg.goods g " +
                "where r.id=:orderId";
        Query query = getSession().createQuery(hql).setParameter("orderId", orderId);
        Iterator itr = query.list().iterator();
        Map<Goods, Integer> map = new HashMap<Goods, Integer>();
        while (itr.hasNext()) {
            Object[] objects = (Object[]) itr.next();
            Goods goods = new Goods();
            goods.setId((Integer) objects[1]);
            goods.setSid((String) objects[2]);
            goods.setName((String) objects[3]);
            goods.setAmount((Integer) objects[4]);
            goods.setPrice((Double) objects[5]);
            goods.setGoodsType((GoodsType) objects[6]);
            map.put(goods, (Integer) objects[0]);
        }
        return map;

    }
}
