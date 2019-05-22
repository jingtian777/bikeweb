package com.example.bikeweb.staticfunc;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Resp<T> {
    /** 错误码 */
    public int errorCode;

    /** 展示给用户的错误信息 */
    public String message;

    /** 分页数据 */

    /** 数据 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public T data;

    public Resp(){
    }
    public Resp(int errorCode,String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
    public Resp(int errorCode,String message,T data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }
    public Resp(T data){
        this.errorCode = 0;
        this.message = "success";
        this.data = data;
    }
    public Resp(Error error){
        this.errorCode = error.code;
        this.message = error.message;
    }
    public Resp(Error error,T data){
        this.errorCode = error.code;
        this.message = error.message;
        this.data = data;
    }
}
