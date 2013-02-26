package com.lecoding.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-26 下午6:39
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("customer/index");
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("customer/login");
        return mv;
    }
}
