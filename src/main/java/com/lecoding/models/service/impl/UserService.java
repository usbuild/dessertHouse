package com.lecoding.models.service.impl;

import com.lecoding.models.dao.IUserDAO;
import com.lecoding.models.pojo.User;
import com.lecoding.models.service.IUserService;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public User findByName(String name) {
        List<User> users = userDAO.findByCriteria(Restrictions.eq("name", name));
        if (users.isEmpty()) return null;
        return users.get(0);
    }
}
