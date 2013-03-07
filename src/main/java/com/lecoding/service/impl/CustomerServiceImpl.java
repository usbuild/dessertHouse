package com.lecoding.service.impl;

import com.lecoding.dao.ICustomerDAO;
import com.lecoding.models.Customer;
import com.lecoding.service.ICustomerService;
import com.lecoding.service.IPayRecordService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午7:33
 */
@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    ICustomerDAO customerDAO;

    @Autowired
    IPayRecordService payRecordService;

    @Override
    public Customer getAnonymousCustomer() {
        return customerDAO.findByName("anonymous");
    }

    @Override
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
    public boolean update(Customer customer) {
        try {
            customerDAO.update(customer);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass()).log(Level.ERROR, ex.getMessage());
            return false;
        }
    }

    @Override
    public Customer findById(int id) {
        return customerDAO.findById(id);
    }

    @Override
    public boolean disable(Customer customer) {
        customer.setStatus(Customer.StatusType.cancel);
        try {
            customerDAO.update(customer);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Customer findByName(String name) {
        return customerDAO.findByName(name);
    }

    @Override
    public boolean pay(Customer customer, int money) {
        customer.setAmount(customer.getAmount() + money);
        if (money >= 100 && customer.getStatus().equals(Customer.StatusType.nouse)) {
            customer.setStatus(Customer.StatusType.active);
        }
        return this.update(customer) && payRecordService.insert(customer, money);
    }

}
