package com.lecoding.models.dao.impl;

import com.lecoding.models.dao.ICustomerDAO;
import com.lecoding.models.po.Customer;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-上午9:45
 */
@Repository
public class CustomerDAOImpl extends BaseDAOSupport<Customer> implements ICustomerDAO {
    @Override
    public Customer findByName(String name) {
        List<Customer> list = findByCriteria(Restrictions.eq("name", name));
        if (list.isEmpty()) return null;
        else return list.get(0);
    }
}
