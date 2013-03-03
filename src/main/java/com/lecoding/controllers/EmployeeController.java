package com.lecoding.controllers;

import com.lecoding.models.po.User;
import com.lecoding.models.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    private User getLoggedUser() {
        return userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @RequestMapping(value = {"/", ""}, headers = "X-Requested-With=XMLHttpRequest")
    public String index(Model model) {
        return "employee/index";
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
