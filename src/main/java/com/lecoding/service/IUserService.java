package com.lecoding.service;

import com.lecoding.models.User;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-26 上午10:55
 */

public interface IUserService {
    User findByName(String name);
}
