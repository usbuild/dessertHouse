package com.lecoding.models.dao.impl;

import com.lecoding.models.dao.ISaleDAO;
import com.lecoding.models.po.Customer;
import com.lecoding.models.po.Sale;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午4:34
 */
@Repository
public class SaleDAOImpl extends BaseDAOSupport<Sale> implements ISaleDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<Sale> allReserves(Customer customer) {
        Query query = getSession().createQuery("SELECT r FROM Sale AS r LEFT JOIN r.customer AS c WHERE c.id = :id AND r.isReserve = :isr").setInteger("id", customer.getId()).setInteger("isr", 1);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Sale> allSales(Customer customer) {
        Query query = getSession().createQuery("SELECT r FROM Sale AS r LEFT JOIN r.customer AS c WHERE c.id = :id").setInteger("id", customer.getId());
        return query.list();
    }
}
