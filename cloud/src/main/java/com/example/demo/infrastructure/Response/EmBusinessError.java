package com.example.demo.infrastructure.Response;

public enum  EmBusinessError implements CommonError {

    PARAMETER_VALIDATION_ERROR(20001,"参数不合法"),
    USER_EXIST(10002, "用户已存在"),
    USER_NOT_EXIST(10001,"用户不存在");


    private int errorCode;
    private String errorMeg;

    EmBusinessError(int errorCode, String errorMeg) {
        this.errorCode = errorCode;
        this.errorMeg = errorMeg;
    }

    @Override
    public int getErrCode() {
        return this.errorCode;
    }

    @Override
    public String getErrMsg() {
        return this.errorMeg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errorMeg = errMsg;
        return this;
    }
}
