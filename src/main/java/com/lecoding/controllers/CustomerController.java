package com.lecoding.controllers;

import com.lecoding.controllers.forms.ChangePassForm;
import com.lecoding.controllers.forms.CustomerInfoForm;
import com.lecoding.controllers.forms.CustomerSignUpForm;
import com.lecoding.models.po.Area;
import com.lecoding.models.po.Customer;
import com.lecoding.models.service.IAreaService;
import com.lecoding.models.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
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
    @Qualifier("customerAuthentication")
    protected AuthenticationManager customerAuthentication;

    @RequestMapping(value = {"/", ""}, headers = "X-Requested-With=XMLHttpRequest")
    public ModelAndView ajaxIndex() {
        ModelAndView mv = new ModelAndView("customer/index");
        mv.addObject("user", customerService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        return mv;
    }

    @RequestMapping(value = "/pay", headers = "X-Requested-With=XMLHttpRequest")
    public String pay(Model model) {
        model.addAttribute("user", customerService.findByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "customer/pay";
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET, headers = "X-Requested-With=XMLHttpRequest")
    public String changePass(Model model) {
        model.addAttribute("passForm", new ChangePassForm());
        return "customer/password";
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST, headers = "X-Requested-With=XMLHttpRequest")
    @ResponseBody
    public String aChangePass(@ModelAttribute("passForm") @Valid ChangePassForm passForm) {
        return null;
    }


    @RequestMapping(value = "/account", method = RequestMethod.GET, headers = "X-Requested-With=XMLHttpRequest")
    public String account(Model model) {
        Customer user = customerService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        CustomerInfoForm form = new CustomerInfoForm();
        form.setAge(user.getAge());
        form.setArea_id(user.getArea().getId());
        form.setGender(user.getGender());
        model.addAttribute("user", form);

        Map<Integer, String> map = new HashMap<Integer, String>();
        for (Area area : areaService.findAll()) {
            map.put(area.getId(), area.getName());
        }
        model.addAttribute("areas", map);
        return "customer/account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST, headers = "X-Requested-With=XMLHttpRequest")
    @ResponseBody
    public String aAccount(@ModelAttribute("user") @Valid CustomerInfoForm infoForm) {
        return null;
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
