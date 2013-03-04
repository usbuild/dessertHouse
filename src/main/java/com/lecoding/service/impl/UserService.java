package com.lecoding.service.impl;

import com.lecoding.dao.IUserDAO;
import com.lecoding.models.User;
import com.lecoding.service.IUserService;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-26 上午10:56
 */
@Service
public class UserService implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Override
    public User findByName(String name) {
        List<User> users = userDAO.findByCriteria(Restrictions.eq("name", name));
        if (users.isEmpty()) return null;
        return users.get(0);
    }
}
