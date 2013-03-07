package com.lecoding.controllers;

import com.lecoding.models.User;
import com.lecoding.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-7 下午5:55
 */
@Controller
@RequestMapping("/user/admin")
public class AdminController {
    @Autowired
    IUserService userService;

    private User getLoggedUser() {
        return userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @RequestMapping(value = {"/info"}, headers = "X-Requested-With=XMLHttpRequest")
    public String info(Model model) {
        model.addAttribute("user", getLoggedUser());
        return "admin/info";
    }

    @RequestMapping(value = {"/", ""}, headers = "X-Requested-With=XMLHttpRequest")
    public String user(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "admin/index";
    }

    @RequestMapping(value = {"/discount"}, headers = "X-Requested-With=XMLHttpRequest")
    public String discount(Model model) {
        return "admin/discount";
    }


    @RequestMapping(value = {"", "/", "/info", "/discount"})
    public String index(Model model) {
        model.addAttribute("user", getLoggedUser());
        return "admin/main";
    }
}
