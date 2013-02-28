package com.lecoding.controllers;

import com.lecoding.models.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.Map;

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

    @RequestMapping({"/search/", "/search"})
    @ResponseBody
    public Map searchGoods(@RequestParam String key, @RequestParam int page) throws UnsupportedEncodingException {
        key = new String(key.getBytes("ISO-8859-1"), "UTF-8");
        return goodsService.searchGoods(key, page);
    }
}
