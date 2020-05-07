package com.yunbanke.daoyun.Web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yunbanke.daoyun.Web.VO.DepartmentVO;
import com.yunbanke.daoyun.Web.VO.SchoolVO;
import com.yunbanke.daoyun.infrastructure.Persistence.DepartmentRepository;
import com.yunbanke.daoyun.infrastructure.Persistence.SchoolRepository;
import com.yunbanke.daoyun.infrastructure.Response.CommonReturnType;
import com.yunbanke.daoyun.infrastructure.entity.Department;
import com.yunbanke.daoyun.infrastructure.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    DepartmentRepository departmentRepository;


    @GetMapping("/getByPage")
    @ResponseBody
    public Page<School> getSchoolByPage(@RequestBody JSONObject jsonObject) {
        int curPage = jsonObject.getIntValue("page");
        Pageable pageable;
        pageable = PageRequest.of(curPage - 1, 10);
        return schoolRepository.findAll(pageable);
    }

    @PostMapping("/addSchool")
    @ResponseBody
    public CommonReturnType add(@RequestBody JSONObject jsonObject) {
        School school = new School();
        String school_name = jsonObject.getString("school_name");
        school.setSchool_name(school_name);
        school = schoolRepository.saveAndFlush(school);
        return CommonReturnType.create(school);
    }

    @PostMapping("/addDepartment")
    @ResponseBody
    public CommonReturnType addDep(@RequestBody JSONObject jsonObject) {
        String department_name = jsonObject.getString("department_name");
        String school_name = jsonObject.getString("school_name");
        Department department = new Department();
        department.setDepartment_name(department_name);
        School school = schoolRepository.findSchoolByschool_name(school_name);
        school.getDepartmentList().add(department);
        schoolRepository.save(school);
        return CommonReturnType.create("success");
    }
}
