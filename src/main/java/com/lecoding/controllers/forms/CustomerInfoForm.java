package com.lecoding.controllers.forms;

import com.lecoding.components.GenderType;
import org.hibernate.validator.constraints.Range;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-2-28 下午4:18
 */
public class CustomerInfoForm {

    GenderType gender;

    @Range(max = 120, min = 10, message = "年龄应该在10-120之间")
    private int age;

    private int area_id;

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }
}
