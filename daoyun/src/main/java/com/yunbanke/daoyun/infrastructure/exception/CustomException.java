package com.yunbanke.daoyun.infrastructure.exception;

public class CustomException extends RuntimeException {
    public CustomException(String msg) {
        super(msg);
    }

    public CustomException() {
        super();
    }
}
