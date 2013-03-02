package com.lecoding.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-26 下午7:07
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping({"/", ""})
    public String index() {
        return "index";
    }
}
