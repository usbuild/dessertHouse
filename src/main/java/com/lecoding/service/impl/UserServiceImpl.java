package com.lecoding.service.impl;

import com.lecoding.components.DessertException;
import com.lecoding.controllers.forms.UserSignUpForm;
import com.lecoding.dao.IShopDAO;
import com.lecoding.dao.IUserDAO;
import com.lecoding.models.Shop;
import com.lecoding.models.User;
import com.lecoding.service.IUserService;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-26 上午10:56
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Autowired
    IShopDAO shopDAO;

    @Override
    public User findByName(String name) {
        List<User> users = userDAO.findByCriteria(Restrictions.eq("name", name));
        if (users.isEmpty()) return null;
        return users.get(0);
    }

    @Override
    public void addUser(UserSignUpForm userSignUpForm) {
        User user = new User();
        if (findByName(userSignUpForm.getName()) != null) throw new DessertException("用户已存在");

        user.setName(userSignUpForm.getName());
        user.setRole(userSignUpForm.getRole());
        if ("employee".equals(user.getRole())) {
            Shop shop = shopDAO.findById(userSignUpForm.getShopId());
            if (shop == null) throw new DessertException("分店不存在");
            user.setShop(shop);
        }
        PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
        user.setPassword(passwordEncoder.encodePassword(userSignUpForm.getPassword(), user.getName()));
        userDAO.save(user);
    }

    @Override
    public List<User> allUsers() {
        return userDAO.findByCriteria();
    }
}
