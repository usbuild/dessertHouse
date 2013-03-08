package com.lecoding.controllers;

import com.lecoding.controllers.forms.SimpleResponse;
import com.lecoding.controllers.forms.UserSignUpForm;
import com.lecoding.models.User;
import com.lecoding.service.IDiscountService;
import com.lecoding.service.IShopService;
import com.lecoding.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    IDiscountService discountService;

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

    @RequestMapping(value = "/add_shop", method = RequestMethod.POST)
    @ResponseBody
    public SimpleResponse addShop(@RequestParam("name") String name, Model model) {
        try {
            shopService.insert(name);
            return new SimpleResponse(0, null);
        } catch (Exception ex) {
            return new SimpleResponse(1, ex.getMessage());
        }
    }


    @RequestMapping(value = "/del_shop", method = RequestMethod.POST)
    @ResponseBody
    public SimpleResponse delShop(@RequestParam("id") int id, Model model) {

        try {
            shopService.deleteById(id);
            return new SimpleResponse(0, null);
        } catch (Exception ex) {

            return new SimpleResponse(1, ex.getMessage());
        }
    }

    @RequestMapping(value = "/del_user", method = RequestMethod.POST)
    @ResponseBody
    public SimpleResponse delUser(@RequestParam("id") int id, Model model) {
        try {
            userService.delById(id);
            return new SimpleResponse(0, null);
        } catch (Exception ex) {
            return new SimpleResponse(1, ex.getMessage());
        }
    }


    @RequestMapping(value = {"/discount"}, headers = "X-Requested-With=XMLHttpRequest")
    public String discount(Model model) {
        model.addAttribute("discount", discountService.allDiscount());
        return "admin/discount";
    }

    @RequestMapping(value = {"/set_discount"}, headers = "X-Requested-With=XMLHttpRequest")
    @ResponseBody
    public SimpleResponse setDiscount(@RequestParam("id") int id, @RequestParam("discount") double discount, Model model) {
        try{
            discountService.setDiscount(id, discount);
            return new SimpleResponse(0, null);
        } catch (Exception ex) {
            return new SimpleResponse(1, ex.getMessage());
        }
    }


    @RequestMapping(value = {"/shop"}, headers = "X-Requested-With=XMLHttpRequest")
    public String shop(Model model) {
        model.addAttribute("shops", shopService.allShops());
        return "admin/shop";
    }


    @RequestMapping(value = {"", "/", "/info", "/discount", "/shop"})
    public String index(Model model) {
        model.addAttribute("user", getLoggedUser());
        return "admin/main";
    }
}
