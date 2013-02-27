package com.lecoding.controllers.forms;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-26 下午9:26
 */
public class CustomerSignUpForm {

    @NotNull(message = "用户名不能为空")
    @Size(min = 4, message = "用户名长度至少为4")
    private String name;

    @NotNull(message = "密码不能为空")
    @Size(min = 5, message = "密码长度至少为5")
    private String password;


    private String repassword;


    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
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


    @AssertTrue(message = "两次密码不匹配")
    public boolean isPasswordMatch() {
        return this.password.equals(this.repassword);
    }
}
