package com.example.demo.Web;

import com.example.demo.infrastructure.DO.School;
import com.example.demo.infrastructure.Repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping("/school")
@Controller
public class SchoolController {

    @Autowired
    SchoolRepository schoolRepository;

    @GetMapping("/add")
    public void Add(String name){
        School school = new School();
        school.setName(name);
        schoolRepository.save(school);
    }

    @GetMapping("/update")
    public void update(int id, String name){
        Optional<School> school = schoolRepository.findById(id);
        if (school.isPresent()){
            school.get().setName(name);
            schoolRepository.save(school.get());
        }
    }

    @GetMapping("/delete")
    public void delete(int id){
        schoolRepository.deleteById(id);
    }

    @GetMapping("/getAll")
    public void getAll(){
        List<School> schoolList = schoolRepository.findAll();
    }
}
