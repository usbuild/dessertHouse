package com.lecoding.controllers;

import com.lecoding.controllers.forms.CustomerSignUpForm;
import com.lecoding.models.po.Customer;
import com.lecoding.models.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

    @Autowired
    ICustomerService customerService;

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
    public String signup_post(@Valid CustomerSignUpForm customerSignUpForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customer/signup";
        } else {
            if (customerService.findByName(customerSignUpForm.getName()) != null) {
                bindingResult.addError(new ObjectError("name", "用户名已存在"));
                return "customer/signup";
            }
            Customer customer = new Customer();
            customer.setName(customerSignUpForm.getName());
            PasswordEncoder encoder = new Md5PasswordEncoder();
            customer.setPassword(encoder.encodePassword(customerSignUpForm.getPassword(), customer.getName()));

            if (customerService.add(customer)) {
                return "redirect:/customer/login";
            } else {
                bindingResult.addError(new ObjectError("dberror", "输入数据库失败"));
                return "customer/signup";
            }
        }
    }
}
