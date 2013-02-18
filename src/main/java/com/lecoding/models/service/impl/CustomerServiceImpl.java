package com.lecoding.models.service.impl;

import com.lecoding.models.dao.ICustomerDAO;
import com.lecoding.models.pojo.Customer;
import com.lecoding.models.service.ICustomerService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
