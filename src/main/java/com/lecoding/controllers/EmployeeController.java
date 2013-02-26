package com.lecoding.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-26 下午5:21
 */
@Controller
@RequestMapping("/user/employee")
public class EmployeeController {

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("employee/index");
        return mv;
    }
}
