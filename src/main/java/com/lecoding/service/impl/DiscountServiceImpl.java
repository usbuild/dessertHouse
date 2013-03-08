package com.lecoding.service.impl;

import com.lecoding.components.DessertException;
import com.lecoding.dao.IDiscountDAO;
import com.lecoding.models.Discount;
import com.lecoding.service.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-8 下午3:23
 */
@Service

public class DiscountServiceImpl implements IDiscountService {

    @Autowired
    IDiscountDAO discountDAO;

    @Override
    public List<Discount> allDiscount() {
        return discountDAO.findAll();
    }

    @Override
    public void setDiscount(int id, double dis) {
        Discount discount = discountDAO.findById(id);
        if (discount == null) throw new DessertException("No Discount Found!");
        discount.setDiscount(dis);
        discountDAO.update(discount);
    }
}
