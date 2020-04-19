package com.example.demo.infrastructure.Response;

public class CommonReturnType {

    //表明对应请求的返回结果 ”success" 或 ”fail“
    private String status;
    //返回的数据
    //若 status 为 success 返回对应数据
    //若 status 为 fail 返回通用的错误码格式
    private Object Data;


    //不指定status 默认返回success
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }

    public static CommonReturnType create(Object result, String status) {
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }
}
