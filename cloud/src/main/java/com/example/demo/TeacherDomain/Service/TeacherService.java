package com.example.demo.TeacherDomain.Service;

import com.example.demo.AuthDomain.DTO.UserDTO;
import com.example.demo.infrastructure.Response.CommonReturnType;

public interface TeacherService {

    CommonReturnType createClass(String name);
}
