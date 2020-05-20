package com.yunbanke.daoyun.infrastructure.exception;

public class CustomUnauthorizedException extends RuntimeException {
    public CustomUnauthorizedException(String msg) {
        super(msg);
    }

    public CustomUnauthorizedException() {
        super();
    }
}
