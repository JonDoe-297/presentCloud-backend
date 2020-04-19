package com.example.demo.Web;

import com.example.demo.infrastructure.DO.Department;
import com.example.demo.infrastructure.DO.School;
import com.example.demo.infrastructure.Repository.DepartmentRepository;
import com.example.demo.infrastructure.Repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping("/department")
@Controller
public class DepController {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    SchoolRepository schoolRepository;

    @GetMapping("/add")
    public void add(String name, int school_id){
        Department department = new Department();
        Optional<School> school = schoolRepository.findById(school_id);
        department.setName(name);

        if (school.isPresent()) {
            department.setSchool(school.get());
            departmentRepository.save(department);
        }
    }


    @GetMapping("delete")
    public void delete(int id){
        departmentRepository.deleteById(id);
    }
}
