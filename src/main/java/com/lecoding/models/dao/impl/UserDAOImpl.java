package com.lecoding.models.dao.impl;

import com.lecoding.models.dao.BaseDAOSupport;
import com.lecoding.models.dao.UserDAO;
import com.lecoding.models.pojo.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-1-27-上午10:03
 */

@Repository("userDAO")
public class UserDAOImpl extends BaseDAOSupport implements UserDAO {

    public Object save(User user) {
        return getSession().save(user);
    }
}
