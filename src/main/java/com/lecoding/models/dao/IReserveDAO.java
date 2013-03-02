package com.lecoding.models.dao;

import com.lecoding.models.po.Customer;
import com.lecoding.models.po.Reserve;
import com.lecoding.models.po.ReserveGoods;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午4:31
 */
public interface IReserveDAO extends IBaseDAO<Reserve> {
    List<ReserveGoods> getGoodsList(int orderId);
    List<Reserve> allReserves(Customer customer);
}
