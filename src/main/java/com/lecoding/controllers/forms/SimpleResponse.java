package com.lecoding.controllers.forms;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-2 下午7:14
 */
public class SimpleResponse {
    private int code;
    private Object data;

    public SimpleResponse() {
    }

    public SimpleResponse(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
