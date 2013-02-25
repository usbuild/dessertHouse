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
        return mv;
    }

    @RequestMapping("/restrict")
    public void restrict() throws Exception {
        System.out.println("Hello");
    }

}
