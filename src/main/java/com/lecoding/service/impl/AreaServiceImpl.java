package com.lecoding.service.impl;

import com.lecoding.dao.IAreaDAO;
import com.lecoding.models.Area;
import com.lecoding.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<Integer, String> allArea() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (Area area : this.findAll()) {
            map.put(area.getId(), area.getName());
        }
        return map;
    }

    @Override
    public Area findById(int id) {
        return areaDAO.findById(id);
    }


}
