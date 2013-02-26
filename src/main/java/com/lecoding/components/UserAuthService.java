package com.lecoding.components;

import com.lecoding.models.pojo.User;
import com.lecoding.models.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-26 上午10:43
 */

public class UserAuthService implements UserDetailsService {

    @Autowired
    IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByName(s);

        if (user == null) {
            throw new UsernameNotFoundException("User " + s + " Not Found!");
        } else {
            List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
            authList.add(new SimpleGrantedAuthority(user.getRole().toUpperCase()));
            return new org.springframework.security.core.userdetails.User(
                    user.getName(), user.getPassword(), authList
            );
        }
    }
}
