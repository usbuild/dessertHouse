package com.lecoding.service.impl;

import com.lecoding.dao.IPayRecordDAO;
import com.lecoding.models.Customer;
import com.lecoding.models.PayRecord;
import com.lecoding.service.IPayRecordService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-1 下午1:18
 */
@Service
public class PayRecordService implements IPayRecordService {

    @Autowired
    IPayRecordDAO payRecordDAO;

    @Override
    public boolean insert(Customer customer, int amount) {
        try {
            PayRecord pr = new PayRecord();
            pr.setAmount(amount);
            pr.setCustomer(customer);
            payRecordDAO.save(pr);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass()).log(Level.ERROR, ex.getMessage());
            return false;
        }
    }

    @Override
    public List<PayRecord> listRecord(Customer customer) {
        return payRecordDAO.findByCriteria(Restrictions.eq("customer", customer));
    }
}
