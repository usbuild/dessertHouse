package com.lecoding.controllers;

import com.lecoding.models.User;
import com.lecoding.service.IManagerService;
import com.lecoding.service.IShopService;
import com.lecoding.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-5 下午1:51
 */
@Controller
@RequestMapping({"/user/manager"})
public class ManagerController {

    @Autowired
    IUserService userService;

    @Autowired
    IShopService shopService;

    @Autowired
    IManagerService managerService;

    private User getLoggedUser() {
        return userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    private Map<String, String> transformMap(Map<String, Integer> map) {
        int total = 0;
        for (String string : map.keySet()) {
            total += map.get(string);
        }
        Map<String, String> newMap = new HashMap<String, String>();
        for (String string : map.keySet()) {
            newMap.put(string, String.format("%.2f", map.get(string) * 1.0 / total));
        }
        return newMap;
    }

    @RequestMapping(value = {"/", ""}, headers = "X-Requested-With=XMLHttpRequest")
    public String customer(Model model) {
        model.addAttribute("area", this.transformMap(managerService.groupCustomerByArea()));
        model.addAttribute("status", this.transformMap(managerService.groupCustomerByStatus()));
        model.addAttribute("gender", this.transformMap(managerService.groupCustomerByGender()));
        model.addAttribute("age", this.transformMap(managerService.groupCustomerByAge()));
        return "manager/customer";
    }

    @RequestMapping(value = "/shop/{id}", headers = "X-Requested-With=XMLHttpRequest")
    public String shop(@PathVariable("id") int id, Model model) {
        model.addAttribute("shops", shopService.allShops());
        model.addAttribute("sales", managerService.saleAmountByShop(id));
        model.addAttribute("reserves", managerService.reserveAmountByShop(id));
        model.addAttribute("selected", id);
        return "manager/shop";
    }

    @RequestMapping(value = "/hot", headers = "X-Requested-With=XMLHttpRequest")
    public String hot(Model model) {
        model.addAttribute("hots", managerService.top10());
        return "manager/hot";
    }


    @RequestMapping(value = "/trend", headers = "X-Requested-With=XMLHttpRequest")
    public String trend(Model model) {
        return "manager/trend";
    }

    @RequestMapping(value = "/info", headers = "X-Requested-With=XMLHttpRequest")
    public String info(Model model) {
        model.addAttribute("user", getLoggedUser());
        return "manager/info";
    }


    @RequestMapping(value = {"/", "", "/shop/{id}", "/trend", "/info", "/hot"})
    public String index(Model model) {
        model.addAttribute("user", getLoggedUser());
        return "manager/main";
    }


}
