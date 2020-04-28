package com.yunbanke.daoyun.Web;

import com.yunbanke.daoyun.Domain.entity.Class;
import com.yunbanke.daoyun.Domain.entity.User;
import com.yunbanke.daoyun.Service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequestMapping("/class")
@CrossOrigin
@RestController
public class ClassController {
    @Autowired
    private ClassService classService;

    @PostMapping("/addClass")
    public void addClass(@RequestParam String className, @RequestParam Integer userId){
        int returned = classService.addClass(className, userId);
    }



    // 课程详情
    @GetMapping("/getClass")
    public Class getClass(@RequestParam String classNum){
        return classService.getClassByClassNum(classNum);
    }


    // 教师详情
    @GetMapping("/getTeacher")
    public User getTeacher(@RequestParam String classNum){
        return classService.getTeacherByClassNum(classNum);
    }

    // 添加学生
    @PostMapping("/addStudents")
    public void addStudents(){
        List<Integer> stu = new ArrayList<>();
        stu.add(4);
        classService.addStudents(stu, "9981245");
    }

    // 移除学生
    @GetMapping("/removeStudents")
    public int removeStudents(@RequestParam List<Integer> stu, @RequestParam String classNum){
        return classService.removeStudents(stu, classNum);
    }

    // TODO 查询课程


    // TODO 添加签到 签到课程、时间、签到记录、成员签到列表、签到结果


    // TODO 上传资源、课程、权限


    // TODO 搜索资源


    // TODO 资源列表


    // TODO 删除资源
}
