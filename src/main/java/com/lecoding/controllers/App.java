package com.lecoding.controllers;

import com.lecoding.models.BankCustomer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app")
public class App {
    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping("/index")
    public ModelAndView index() {
        Session session = sessionFactory.openSession();
        BankCustomer bankCustomer = (BankCustomer)session.get(BankCustomer.class, 1);
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("data", bankCustomer.getName());
        return mv;
    }
}
