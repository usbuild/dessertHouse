package com.lecoding.controllers.forms;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-8 上午8:51
 */
public class UserSignUpForm {
    @NotNull
    @Size(min = 3, message = "用户名长度应不小于3")
    private String name;

    @NotNull
    @Size(min = 4, message = "密码长度应不小于4")
    private String password;
    private String repassword;

    private String role;
    private int shopId;


    @AssertTrue(message = "密码不匹配")
    public boolean isPasswordMatch() {
        return this.password.equals(this.repassword);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }
}
