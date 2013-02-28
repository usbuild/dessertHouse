package com.lecoding.models.service.impl;

import com.lecoding.models.dao.IAreaDAO;
import com.lecoding.models.po.Area;
import com.lecoding.models.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-28 下午4:45
 */
@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    IAreaDAO areaDAO;

    @Override
    public List<Area> findAll() {
        return areaDAO.findByCriteria();
    }
}
