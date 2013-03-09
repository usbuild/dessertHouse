package com.lecoding.service.impl;

import com.lecoding.dao.ICustomerDAO;
import com.lecoding.service.IManagerService;
import org.apache.commons.collections.map.ListOrderedMap;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-5 下午3:55
 */
@Service
public class ManagerServiceImpl implements IManagerService {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    ICustomerDAO customerDAO;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    private Map<String, Integer> groupQuery(String sql) {
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        List<Object[]> list = (List<Object[]>) sqlQuery.list();
        Map<String, Integer> map = new ListOrderedMap();
        for (Object[] objects : list) {
            if (objects[0] == null) objects[0] = "其它";
            if (objects[1] instanceof Double) {
                map.put(objects[0].toString(), ((Double) objects[1]).intValue());
            } else if (objects[1] instanceof BigInteger) {
                map.put(objects[0].toString(), ((BigInteger) objects[1]).intValue());
            } else if (objects[1] instanceof BigDecimal) {
                map.put(objects[0].toString(), ((BigDecimal) objects[1]).intValue());
            } else if (objects[1] instanceof Integer) {
                map.put(objects[0].toString(), (Integer) objects[1]);
            }
        }
        return map;
    }

    @Override
    public Map<String, Integer> groupCustomerByStatus() {
        String sql = "SELECT c.status, count(*) FROM customer c GROUP BY c.status";
        Map<String, Integer> map = groupQuery(sql);
        Map<String, Integer> newMap = new HashMap<String, Integer>();
        for (String key : map.keySet()) {
            if ("nouse".equals(key)) {
                newMap.put("未激活", map.get(key));
            } else if ("active".equals(key)) {
                newMap.put("已激活", map.get(key));
            } else if ("pause".equals(key)) {
                newMap.put("暂停", map.get(key));
            } else if ("cancel".equals(key)) {
                newMap.put("终止", map.get(key));
            }
        }
        return newMap;
    }

    @Override
    public Map<String, Integer> groupCustomerByAge() {
        String sql = "select ages,count(*) from (\n" +
                "\tselect\n" +
                "\tcase\n" +
                "\twhen age>=1 and age<=10 then '1-10'\n" +
                "\twhen age>=11 and age<=20 then '11-20'\n" +
                "\twhen age>=21 and age<=30 then '21-30'\n" +
                "\twhen age>=31 and age<=40 then '31-40'\n" +
                "\twhen age>=41 and age<=50 then '41-50'\n" +
                "\twhen age>=51 and age<=60 then '51-60'\n" +
                "\twhen age>=61 and age<=70 then '61-70'\n" +
                "\twhen age>=71 and age<=80 then '71-80'\n" +
                "\twhen age>=81 and age<=90 then '81-90'\n" +
                "\twhen age>=91 and age<=100 then '91-100'\n" +
                "\telse 'other'\n" +
                "\tend\n" +
                "as ages,name from customer) tmp group by tmp.ages;";
        return this.groupQuery(sql);
    }

    @Override
    public Map<String, Integer> groupCustomerByGender() {
        String sql = "SELECT c.gender, count(*) FROM customer c GROUP BY c.gender";
        Map<String, Integer> map = groupQuery(sql);
        Map<String, Integer> newMap = new HashMap<String, Integer>();
        for (String key : map.keySet()) {
            if ("male".equals(key)) {
                newMap.put("男", map.get(key));
            } else if ("female".equals(key)) {
                newMap.put("女", map.get(key));
            }
        }
        return newMap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Integer> saleAmountByShop(int shop_id) {
        String sql = "select st.sale_time, sum(sg.amount * st.price) from sale_goods sg join store st on sg.store_id=st.id join sale sl on sg.sale_id=sl.id where st.shop_id = " + shop_id + " and sl.is_reserve=0 group by sale_time order by sale_time";
        Map<String, Integer> map = groupQuery(sql);
        Map<String, Integer> newMap = new ListOrderedMap();
        for (String key : map.keySet()) {
            newMap.put(key.split(" ")[0], map.get(key));
        }
        return newMap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Integer> reserveAmountByShop(int shop_id) {
        String sql = "select st.sale_time, sum(sg.amount * st.price) from sale_goods sg join store st on sg.store_id=st.id join sale sl on sg.sale_id=sl.id where st.shop_id = " + shop_id + " and sl.is_reserve=1 group by sale_time order by sale_time";
        Map<String, Integer> map = groupQuery(sql);
        Map<String, Integer> newMap = new ListOrderedMap();
        for (String key : map.keySet()) {
            newMap.put(key.split(" ")[0], map.get(key));
        }
        return newMap;
    }

    @Override
    public Map<String, Integer> top10() {
        String sql = "select gd.name,sum(sg.amount) as num from sale_goods sg left join store st on sg.store_id=st.id left join goods gd on st.goods_id=gd.id group by gd.sid order by num desc limit 0, 10;";
        return groupQuery(sql);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Integer> saleAmount() {
        String sql = "select st.sale_time, sum(sg.amount * st.price) from sale_goods sg join store st on sg.store_id=st.id join sale sl on sg.sale_id=sl.id group by sale_time order by st.sale_time";
        Map<String, Integer> map = groupQuery(sql);
        Map<String, Integer> newMap = new ListOrderedMap();
        for (String key : map.keySet()) {
            newMap.put(key.split(" ")[0], map.get(key));
        }
        return newMap;
    }

    @Override
    public Map<String, Integer> groupSaleType() {
        String sql = "select gt.name, sg.amount from sale_goods sg left join store st on sg.store_id = st.id left join goods gd on st.goods_id=gd.id left join goods_type gt on gd.type = gt.id group by gd.type";
        return groupQuery(sql);
    }

    public Map<String, Integer> groupCustomerByArea() {
        String sql = "SELECT a.name, count(*) FROM customer c LEFT JOIN area a ON c.area_id = a.id GROUP BY c.area_id";
        return this.groupQuery(sql);
    }

}
