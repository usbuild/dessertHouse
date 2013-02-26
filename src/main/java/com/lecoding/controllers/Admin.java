package com.lecoding.controllers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-26 下午2:25
 */
@Controller
@RequestMapping("/user")
public class Admin {
    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("admin/login");
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/")
    public String index() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<GrantedAuthority> list = (Collection<GrantedAuthority>) userDetails.getAuthorities();

        for (GrantedAuthority authority : list) {
            if (authority.getAuthority().equals("EMPLOYEE")) {
                return "redirect:/user/employee";
            } else if (authority.getAuthority().equals("MANAGER")) {
                return "redirect:/user/manager";

            } else if (authority.getAuthority().equals("ADMIN")) {
                return "redirect:/user/admin";
            }
        }
        return "forward:/user/login";
    }
}
