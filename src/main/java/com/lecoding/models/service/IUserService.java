package com.lecoding.models.service;

import com.lecoding.models.po.User;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-26 上午10:55
 */

public interface IUserService {
    User findByName(String name);
}
