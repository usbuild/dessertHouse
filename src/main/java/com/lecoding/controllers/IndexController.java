package com.lecoding.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-26 下午7:07
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/json")
    public
    @ResponseBody
    Map json() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Hello", "world");
        return map;
    }
}
