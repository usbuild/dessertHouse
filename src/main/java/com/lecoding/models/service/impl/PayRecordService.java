package com.lecoding.models.service.impl;

import com.lecoding.models.dao.IPayRecordDAO;
import com.lecoding.models.po.Customer;
import com.lecoding.models.po.PayRecord;
import com.lecoding.models.service.IPayRecordService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
