package com.yunbanke.daoyun.Web.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

//
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class RetResponse<T> implements Serializable {
    //请求成功返回码为：0000
    private static final String successCode = "200";
    //返回数据
    private T data;

    private String code;
    private String msg;

    /**
     * "2001", "资源未找到"
     * "2002", "输入不合法"
     * "2003", "已经有在进行的签到任务"
     * "2004", "设定时间不正确"
     * "2005", "签到失败，签到已过期"
     * "2006", "签到失败，签到码错误"
     * "5001", "数据库存取错误"
     */

    public RetResponse(){
        this.code = successCode;
        this.msg = "请求成功";
    }

    public RetResponse(String code, String msg){
        this();
        this.code = code;
        this.msg = msg;
    }

    public RetResponse(String code, String msg, T data){
        this();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RetResponse(T data){
        this();
        this.data = data;
    }

    public static String getSuccessCode() {
        return successCode;
    }

    public T getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
