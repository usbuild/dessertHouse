package com.lecoding.models.service;

import com.lecoding.models.po.Area;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-28 下午4:45
 */
public interface IAreaService {
    List<Area> findAll();
    Map<Integer, String> allArea();
    Area findById(int id);
}
