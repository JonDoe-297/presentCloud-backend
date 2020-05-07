package com.yunbanke.daoyun.Web;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.yunbanke.daoyun.Web.VO.DepartmentVO;
import com.yunbanke.daoyun.infrastructure.Persistence.DepartmentRepository;
import com.yunbanke.daoyun.infrastructure.Persistence.SchoolRepository;
import com.yunbanke.daoyun.infrastructure.Response.CommonReturnType;
import com.yunbanke.daoyun.infrastructure.entity.Department;
import com.yunbanke.daoyun.infrastructure.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    SchoolRepository schoolRepository;

    @PostMapping(value = "/getDepartmentBySchool")
    @ResponseBody
    public CommonReturnType getDepartmentBySchool(@RequestBody JSONObject jsonObject) {
        String school_name = jsonObject.getString("school_name");
        School school = schoolRepository.findSchoolByschool_name(school_name);
        List<DepartmentVO> departmentVOList = new ArrayList<>();
        for (Department department : school.getDepartmentList()) {
            DepartmentVO departmentVO = new DepartmentVO(department.getDepartment_id(), department.getDepartment_name());
            departmentVOList.add(departmentVO);
        }
        return CommonReturnType.create(departmentVOList);
    }

}
