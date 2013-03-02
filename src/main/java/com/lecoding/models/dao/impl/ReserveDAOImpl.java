package com.lecoding.models.dao.impl;

import com.lecoding.models.dao.IReserveDAO;
import com.lecoding.models.po.Customer;
import com.lecoding.models.po.Reserve;
import com.lecoding.models.po.ReserveGoods;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午4:34
 */
@Repository
public class ReserveDAOImpl extends BaseDAOSupport<Reserve> implements IReserveDAO {

    @Override
    public List<ReserveGoods> getGoodsList(int orderId) {
        Reserve reserve = this.findById(orderId);
        if (reserve == null) return null;
        return reserve.getReserveGoods();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Reserve> allReserves(Customer customer) {
        Query query = getSession().createQuery("SELECT r FROM Reserve AS r LEFT JOIN r.customer AS c WHERE c.id = :id").setInteger("id", customer.getId());
        return query.list();
    }
}
