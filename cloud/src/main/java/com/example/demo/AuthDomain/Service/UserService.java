package com.example.demo.AuthDomain.Service;

import com.example.demo.infrastructure.Response.CommonReturnType;
import org.springframework.stereotype.Service;


public interface UserService {
    CommonReturnType Register(String username, String password, int role_type);
}
