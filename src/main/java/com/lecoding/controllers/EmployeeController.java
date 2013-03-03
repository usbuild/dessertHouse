package com.lecoding.controllers;

import com.lecoding.controllers.forms.BuyForm;
import com.lecoding.controllers.forms.SimpleResponse;
import com.lecoding.models.po.Customer;
import com.lecoding.models.po.User;
import com.lecoding.models.service.ICustomerService;
import com.lecoding.models.service.ISaleService;
import com.lecoding.models.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-26 下午5:21
 */
@Controller
@RequestMapping("/user/employee")
public class EmployeeController {
    @Autowired
    IUserService userService;
    @Autowired
    ICustomerService customerService;
    @Autowired
    ISaleService saleService;

    private User getLoggedUser() {
        return userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @RequestMapping(value = {"/", ""}, headers = "X-Requested-With=XMLHttpRequest")
    public String index(Model model) {
        return "employee/index";
    }

    @RequestMapping(value = "/bought", headers = "X-Requested-With=XMLHttpRequest")
    @ResponseBody
    public SimpleResponse bought(@ModelAttribute BuyForm buyForm) {
        try {
            saleService.addSale(buyForm);
            return new SimpleResponse(0, null);
        } catch (Exception ex) {
            return new SimpleResponse(1, ex.getMessage());
        }
    }

    @RequestMapping(value = "/customer/{name}")
    @ResponseBody
    public SimpleResponse customerInfo(@PathVariable("name") String name) {
        Customer customer = customerService.findByName(name);
        if (customer != null) {
            return new SimpleResponse(0, customer);
        } else {
            return new SimpleResponse(1, customer);
        }
    }


    @RequestMapping(value = {"/store"}, headers = "X-Requested-With=XMLHttpRequest")
    public String updateStore(Model model) {
        return "employee/store";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET, headers = "X-Requested-With=XMLHttpRequest")
    public String userInfo(Model model) {
        return "employee/info";
    }

    @RequestMapping({"/", "", "/store", "info"})
    public String main(Model model) {
        model.addAttribute("user", getLoggedUser());
        return "employee/main";
    }
}
