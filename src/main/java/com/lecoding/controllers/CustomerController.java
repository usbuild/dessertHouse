package com.lecoding.controllers;

import com.lecoding.models.form.CustomerSignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup_get(Model model) {
        model.addAttribute(new CustomerSignUpForm());
        return "customer/signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup_post(@Valid CustomerSignUpForm customer, BindingResult bindingResult) {
        return null;
    }
}
