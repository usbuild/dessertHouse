package com.lecoding.controllers;

import com.lecoding.controllers.forms.GoodsForm;
import com.lecoding.controllers.forms.SearchGoodsForm;
import com.lecoding.controllers.forms.SimpleResponse;
import com.lecoding.controllers.forms.StoreForm;
import com.lecoding.models.User;
import com.lecoding.service.IGoodsService;
import com.lecoding.service.IStoreService;
import com.lecoding.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-27 下午9:24
 */
@Controller
@RequestMapping("")
public class GoodsController {
    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }

    @Autowired
    IGoodsService goodsService;
    @Autowired
    IStoreService storeService;

    @Autowired
    IUserService userService;

    @RequestMapping({"/customer/search/", "/customer/search"})
    @ResponseBody
    public Map searchGoods(@Valid SearchGoodsForm goodsForm, @RequestParam int page) throws UnsupportedEncodingException {
        goodsForm.setKey(new String(goodsForm.getKey().getBytes("ISO-8859-1"), "UTF-8"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", storeService.searchStore(goodsForm.getShopId(), goodsForm.getKey(), goodsForm.getDate()));
        return map;
    }

    @RequestMapping({"/user/employee/search", "/user/employee/search/"})
    @ResponseBody
    public Map userSearchGoods(@RequestParam("key") String key) throws UnsupportedEncodingException {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Map<String, Object> map = new HashMap<String, Object>();
        key = new String(key.getBytes("ISO-8859-1"), "UTF-8");
        map.put("data", storeService.searchStore(user.getShop().getId(), key, new Date()));
        return map;
    }

    @RequestMapping({"/user/employee/store/list", "/user/employee/store/list/"})
    @ResponseBody
    public Map userSearchGoods(@RequestParam("date") Date date) throws UnsupportedEncodingException {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", storeService.searchStore(user.getShop().getId(), "", date));
        return map;
    }


    @RequestMapping({"/user/employee/store/add", "/user/employee/store/add/"})
    @ResponseBody
    public SimpleResponse addStoreItem(@RequestParam StoreForm storeForm) {
        return null;
    }

    @RequestMapping({"/user/employee/goods/add"})
    @ResponseBody
    public SimpleResponse addNewGoods(@Valid  GoodsForm goodsForm) {
        try {
            return new SimpleResponse(0, goodsService.addGoods(goodsForm));
        } catch (Exception ex) {
            return new SimpleResponse(1, null);
        }
    }


    @RequestMapping({"/user/employee/type/add"})
    @ResponseBody
    public SimpleResponse addGoodsType(@RequestParam("name") String name) {
        if (goodsService.addGoodsType(name)) return new SimpleResponse(0, null);
        else return new SimpleResponse(1, null);
    }

    @RequestMapping({"/user/employee/store/del", "/user/employee/store/del/"})
    public SimpleResponse delStoreItem(@RequestParam int id) {

        return new SimpleResponse(0, null);
    }

}
