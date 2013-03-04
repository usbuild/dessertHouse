package com.lecoding.components;

import com.lecoding.models.Customer;
import com.lecoding.service.ICustomerService;
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
 * DateTime: 13-2-26 上午11:26
 */
public class CustomerAuthService implements UserDetailsService {
    @Autowired
    ICustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerService.findByName(username);
        if (customer == null) {
            throw new UsernameNotFoundException("User " + username + " Not Found!");
        } else {
            List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
            authList.add(new SimpleGrantedAuthority("CUSTOMER"));
            if (customer.getStatus().equals(Customer.StatusType.nouse) || customer.getStatus().equals(Customer.StatusType.active)) {
                return new org.springframework.security.core.userdetails.User(
                        customer.getName(), customer.getPassword(), authList
                );
            } else {
                return new org.springframework.security.core.userdetails.User(
                        customer.getName(), customer.getPassword(), false, true, true, true, authList
                );
            }
        }
    }
}
