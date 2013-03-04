package com.lecoding.controllers;

import com.lecoding.components.Utils;
import com.lecoding.controllers.forms.*;
import com.lecoding.models.Area;
import com.lecoding.models.Customer;
import com.lecoding.models.Sale;
import com.lecoding.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

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

    @Autowired
    IAreaService areaService;

    @Autowired
    IPayRecordService payRecordService;
    @Autowired
    IShopService shopService;

    @Autowired
    ISaleService saleService;

    @Autowired
    @Qualifier("customerAuthentication")
    protected AuthenticationManager customerAuthentication;

    @RequestMapping(value = {"/", ""}, headers = "X-Requested-With=XMLHttpRequest")
    public String ajaxIndex(Model model) {
        model.addAttribute("user", customerService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("dates", Utils.getNextWeek());
        model.addAttribute("searchGoodsForm", new SearchGoodsForm());
        model.addAttribute("shops", shopService.allShops());
        return "customer/index";
    }

    @RequestMapping("/disable")

    public @ResponseBody SimpleResponse disableAccount() {
        if (customerService.disable(customerService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()))) {
            SecurityContextHolder.clearContext();
            return new SimpleResponse(0, null);
        } else {
            return new SimpleResponse(1, null);
        }
    }


    @RequestMapping(value = {"/reserve"}, headers = "X-Requested-With=XMLHttpRequest")
    @ResponseBody
    public SimpleResponse reserve(@RequestParam Map<String, String> map) {
        try {
            saleService.addReserve(map, customerService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()));
            return new SimpleResponse(0, null);
        } catch (Exception ex) {
            return new SimpleResponse(1, ex.getMessage());
        }
    }

    @RequestMapping(value = "/sale/{id}")
    public String reserveDetail(@PathVariable("id") int id, Model model) {
        Sale sale = saleService.findById(id);
        if (sale == null) return null;
        if (!sale.getCustomer().getName().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
            return null;
        model.addAttribute("list", sale.getSaleGoods());
        return "customer/record_detail";
    }

    @RequestMapping(value = {"/record"}, headers = "X-Requested-With=XMLHttpRequest")
    public String ajaxRecord(Model model) {
        model.addAttribute("reserves", saleService.allSales(customerService.findByName(SecurityContextHolder.getContext().getAuthentication().getName())));
        return "customer/record";
    }


    @RequestMapping(value = "/pay", method = RequestMethod.GET, headers = "X-Requested-With=XMLHttpRequest")
    public String pay(Model model) {
        Customer customer = customerService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", customer);
        model.addAttribute("records", payRecordService.listRecord(customer));
        return "customer/pay";
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST, headers = "X-Requested-With=XMLHttpRequest")
    public String pPay(@RequestParam("amount") int money, Model model) {
        Customer customer = customerService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        if (money >= 100 && customer.getStatus().equals(Customer.StatusType.nouse)) {
            model.addAttribute("upgrade", true);
        }
        if (customerService.pay(customer, money)) {
            model.addAttribute("success", true);
        } else {
            model.addAttribute("fail", true);
        }
        model.addAttribute("records", payRecordService.listRecord(customer));
        return "customer/pay";
    }

    @RequestMapping(value = "/profile", headers = "X-Requested-With=XMLHttpRequest")
    public String profile(Model model) {
        model.addAttribute("user", customerService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "customer/profile";
    }


    @RequestMapping(value = "/password", method = RequestMethod.GET, headers = "X-Requested-With=XMLHttpRequest")
    public String changePass(Model model) {
        model.addAttribute("passForm", new ChangePassForm());
        return "customer/password";
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST, headers = "X-Requested-With=XMLHttpRequest")
    public String aChangePass(@ModelAttribute("passForm") @Valid ChangePassForm passForm, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    username,
                    passForm.getOldPassword()
            );
            try {
                customerAuthentication.authenticate(token);
                Customer customer = customerService.findByName(username);
                PasswordEncoder encoder = new Md5PasswordEncoder();
                customer.setPassword(encoder.encodePassword(passForm.getPassword(), customer.getName()));
                if (customerService.update(customer)) {
                    model.addAttribute("success", true);
                } else {
                    bindingResult.addError(new ObjectError("customer", "修改密码失败"));
                }
            } catch (BadCredentialsException ignored) {
                bindingResult.addError(new ObjectError("oldPassword", "旧密码不正确"));
            }
        }
        return "customer/password";
    }


    @RequestMapping(value = "/account", method = RequestMethod.GET, headers = "X-Requested-With=XMLHttpRequest")
    public String account(Model model) {
        Customer user = customerService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        CustomerInfoForm form = new CustomerInfoForm();
        form.setAge(user.getAge());
        form.setArea_id(user.getArea().getId());
        form.setGender(user.getGender());
        model.addAttribute("user", form);

        model.addAttribute("areas", areaService.allArea());
        return "customer/account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST, headers = "X-Requested-With=XMLHttpRequest")
    public String aAccount(@ModelAttribute("user") @Valid CustomerInfoForm infoForm, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            Customer user = customerService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
            Area area = areaService.findById(infoForm.getArea_id());
            if (area == null) {
                bindingResult.addError(new ObjectError("area", "地区不存在"));
            } else {
                user.setArea(area);
                user.setAge(infoForm.getAge());
                user.setGender(infoForm.getGender());
                if (!customerService.update(user)) {
                    bindingResult.addError(new ObjectError("customer", "修改用户失败"));
                } else {
                    model.addAttribute("success", true);
                }
            }
        }
        model.addAttribute("areas", areaService.allArea());
        return "customer/account";
    }


    @RequestMapping({"/", "", "/account", "/pay", "/password", "/record"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("customer/main");
        mv.addObject("user", customerService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()));
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
    public String signup_post(@Valid CustomerSignUpForm customerSignUpForm, BindingResult bindingResult,
                              HttpServletRequest request, HttpServletResponse response) {
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

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(customerSignUpForm.getName(), customerSignUpForm.getPassword());
                request.getSession();
                token.setDetails(new WebAuthenticationDetails(request));
                Authentication authentication = customerAuthentication.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return "redirect:/customer/";
            } else {
                bindingResult.addError(new ObjectError("dberror", "输入数据库失败"));
                return "customer/signup";
            }
        }
    }
}
