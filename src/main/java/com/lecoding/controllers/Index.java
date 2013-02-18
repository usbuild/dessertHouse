package com.lecoding.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class Index {
    @RequestMapping("/")
    public ModelAndView index() throws Exception {
        ModelAndView mv = new ModelAndView("index");
//        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[0];
        return mv;
    }
}
