package com.lecoding.models.service.impl;

import com.lecoding.models.dao.ICustomerDAO;
import com.lecoding.models.po.Customer;
import com.lecoding.models.service.ICustomerService;
import com.lecoding.models.service.IPayRecordService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    IPayRecordService payRecordService;

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
    public Customer findByName(String name) {
        List<Customer> list = customerDAO.findByCriteria(Restrictions.eq("name", name));
        if (list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    public boolean pay(Customer customer, int money) {
        customer.setAmount(customer.getAmount() + money);
        if (money >= 100 && customer.getStatus().equals(Customer.StatusType.nouse)) {
            customer.setStatus(Customer.StatusType.active);
        }
        return this.add(customer) && payRecordService.insert(customer, money);
    }

}
