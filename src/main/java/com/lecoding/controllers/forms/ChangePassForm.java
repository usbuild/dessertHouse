package com.lecoding.controllers.forms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-28 下午5:10
 */
public class ChangePassForm {
    @NotNull
    @NotEmpty(message = "密码不能为空")
    String oldPassword;
    @Size(min = 5, message = "新密码长度不能小于5")
    String password;
    String rePassword;


    @AssertTrue(message = "两次输入的密码不匹配")
    public boolean isPasswordMatch() {
        return this.password.equals(this.rePassword);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
