package com.lecoding.controllers;

import com.lecoding.components.Utils;
import com.lecoding.controllers.forms.BuyForm;
import com.lecoding.controllers.forms.GoodsForm;
import com.lecoding.controllers.forms.SimpleResponse;
import com.lecoding.models.Customer;
import com.lecoding.models.User;
import com.lecoding.service.*;
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
    @Autowired
    IStoreService storeService;
    @Autowired
    IGoodsService goodsService;

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
        User user = getLoggedUser();
        model.addAttribute("dates", Utils.getNextWeek());
        model.addAttribute("goods", storeService.searchStore(user.getShop().getId(), "", Utils.getNextWeek().get(0)));

        return "employee/store";
    }

    @RequestMapping(value = "/goods", headers = "X-Requested-With=XMLHttpRequest")
    public String goodsList(Model model) {
        model.addAttribute("goodsForm", new GoodsForm());
        model.addAttribute("types", goodsService.allGoodsType());
        model.addAttribute("goodsList", goodsService.allGoods());
        return "employee/goods";
    }
    @RequestMapping(value = "/goods/list", headers = "X-Requested-With=XMLHttpRequest")
    public String listGoods(Model model){
        model.addAttribute("goodsList", goodsService.allGoods());
        return "employee/listgoods";
    }


    @RequestMapping(value = "/info", method = RequestMethod.GET, headers = "X-Requested-With=XMLHttpRequest")
    public String userInfo(Model model) {
        return "employee/info";
    }


    @RequestMapping({"/", "", "/store", "/info", "/goods"})
    public String main(Model model) {
        model.addAttribute("user", getLoggedUser());
        return "employee/main";
    }
}
