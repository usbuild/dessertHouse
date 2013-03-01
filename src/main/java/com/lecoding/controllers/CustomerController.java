package com.lecoding.controllers;

import com.lecoding.controllers.forms.ChangePassForm;
import com.lecoding.controllers.forms.CustomerInfoForm;
import com.lecoding.controllers.forms.CustomerSignUpForm;
import com.lecoding.models.po.Area;
import com.lecoding.models.po.Customer;
import com.lecoding.models.service.IAreaService;
import com.lecoding.models.service.ICustomerService;
import com.lecoding.models.service.IPayRecordService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    IAreaService areaService;

    @Autowired
    IPayRecordService payRecordService;

    @Autowired
    @Qualifier("customerAuthentication")
    protected AuthenticationManager customerAuthentication;

    @RequestMapping(value = {"/", ""}, headers = "X-Requested-With=XMLHttpRequest")
    public ModelAndView ajaxIndex() {
        ModelAndView mv = new ModelAndView("customer/index");
        mv.addObject("user", customerService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        return mv;
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
                if (customerService.add(customer)) {
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
                if (!customerService.add(user)) {
                    bindingResult.addError(new ObjectError("customer", "修改用户失败"));
                } else {
                    model.addAttribute("success", true);
                }
            }
        }
        model.addAttribute("areas", areaService.allArea());
        return "customer/account";
    }


    @RequestMapping({"/", "", "/account", "/pay", "/password"})
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
