package com.lecoding.controllers;

import com.lecoding.controllers.forms.SimpleResponse;
import com.lecoding.controllers.forms.UserSignUpForm;
import com.lecoding.models.User;
import com.lecoding.service.IShopService;
import com.lecoding.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    IShopService shopService;

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
        model.addAttribute("user", new UserSignUpForm());
        model.addAttribute("shops", shopService.allShops());
        return "admin/index";
    }

    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    @ResponseBody
    public SimpleResponse addUser(@ModelAttribute UserSignUpForm userSignUpForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                sb.append(error);
            }
            return new SimpleResponse(1, sb.toString());
        } else {

            try {
                userService.addUser(userSignUpForm);
            } catch (Exception ex) {
                return new SimpleResponse(1, ex.getMessage());
            }
            return new SimpleResponse(0, null);
        }
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
