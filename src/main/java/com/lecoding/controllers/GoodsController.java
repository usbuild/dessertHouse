package com.lecoding.controllers;

import com.lecoding.models.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-27 下午9:24
 */
@Controller
@RequestMapping({"/customer", "/employee"})
public class GoodsController {

    @Autowired
    IGoodsService goodsService;

    @RequestMapping("/search/{key}")
    @ResponseBody
    public List searchGoods(@PathVariable String key) {
        return goodsService.searchGoods(key);
    }
}
