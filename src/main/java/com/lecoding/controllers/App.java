package com.lecoding.controllers;

import com.lecoding.models.dao.UserDAO;
import com.lecoding.models.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app")
public class App {
    @Autowired
    @Qualifier("userDAO")
    UserDAO userDAO;

    @RequestMapping("/")
    public ModelAndView hello() throws Exception {
        ModelAndView mv = new ModelAndView("index");
        User user = new User();
        user.setName("hello");
        user.setPassword("123");
        user.setSalt("123");
        user.setAmount(1200);
        Object obj = userDAO.save(user);
        mv.addObject("data", obj.toString());
        return mv;
    }
}
