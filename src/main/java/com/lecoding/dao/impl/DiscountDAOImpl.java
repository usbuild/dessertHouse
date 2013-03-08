package com.lecoding.dao.impl;

import com.lecoding.dao.IDiscountDAO;
import com.lecoding.models.Discount;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-8 下午3:13
 */
@Repository
public class DiscountDAOImpl extends BaseDAOSupport<Discount> implements IDiscountDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<Discount> findAll() {
        Query query = getSession().createQuery("FROM Discount d ORDER BY d.level ASC");
        return (List<Discount>) query.list();
    }
}
