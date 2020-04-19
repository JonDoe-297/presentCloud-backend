package com.example.demo.infrastructure.Response;

public interface CommonError {
    int getErrCode();
    String getErrMsg();
    CommonError setErrMsg(String errMsg);
}
