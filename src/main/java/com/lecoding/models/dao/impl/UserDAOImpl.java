package com.lecoding.models.dao.impl;

import com.lecoding.models.dao.IUserDAO;
import com.lecoding.models.pojo.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-26 上午10:50
 */
@Repository
public class UserDAOImpl extends BaseDAOSupport<User> implements IUserDAO {
}
