package com.lecoding.models.service.impl;

import com.lecoding.models.dao.ICustomerDAO;
import com.lecoding.models.po.Customer;
import com.lecoding.models.service.ICustomerService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午7:33
 */
@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    ICustomerDAO customerDAO;

    @Override
    @Transactional
    public boolean add(Customer customer) {
        try {
            customerDAO.save(customer);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass()).log(Level.ERROR, ex.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public Customer findById(int id) {
        return customerDAO.findById(id);
    }

    @Override
    @Transactional
    public Customer findByName(String name) {
        List<Customer> list = customerDAO.findByCriteria(Restrictions.eq("name", name));
        if (list.isEmpty()) return null;
        else return list.get(0);
    }
}
