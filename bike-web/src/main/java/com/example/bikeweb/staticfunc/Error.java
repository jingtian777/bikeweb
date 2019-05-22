package com.example.bikeweb.staticfunc;

import org.apache.ibatis.annotations.Update;

public enum Error {
    Succ(0, "成功"),
    Fail(-1,"失败"),
    NoLogin(1,"未登陆"),
    NoPermission(2,"没权限"),

    Registe_FAIL(1000,"用户已存在"),
    Login_FAIL(1001,"登陆失败"),
    Update_FAIL(1002,"用户更新失败");

    public int code;
    public String message;

    private Error(int code, String message) {
        this.message = message;
        this.code = code;
    }
}
