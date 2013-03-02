package com.lecoding.controllers;

import com.lecoding.controllers.forms.SearchGoodsForm;
import com.lecoding.models.service.IGoodsService;
import com.lecoding.models.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
@RequestMapping({"/customer", "/employee"})
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

    @RequestMapping({"/search/", "/search"})
    @ResponseBody
    public Map searchGoods(@Valid SearchGoodsForm goodsForm, @RequestParam int page) throws UnsupportedEncodingException {
        goodsForm.setKey(new String(goodsForm.getKey().getBytes("ISO-8859-1"), "UTF-8"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", storeService.searchStore(goodsForm.getShopId(), goodsForm.getKey(), goodsForm.getDate()));
        return map;
    }
}
