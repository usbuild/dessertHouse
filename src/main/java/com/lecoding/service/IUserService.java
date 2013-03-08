package com.lecoding.service;

import com.lecoding.controllers.forms.UserSignUpForm;
import com.lecoding.models.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-26 上午10:55
 */

public interface IUserService {
    User findByName(String name);
    void delById(int id);
    void addUser(UserSignUpForm userSignUpForm);
    List<User> allUsers();
}
