package com.yunbanke.daoyun.Web;


import com.yunbanke.daoyun.Service.ClassService;
import com.yunbanke.daoyun.infrastructure.Types.FullClass;
import com.yunbanke.daoyun.infrastructure.entity.Class;
import com.yunbanke.daoyun.infrastructure.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @GetMapping("/getClassByClassnum")
    public Class getClassByClassnum(@RequestParam String classNum){
        return classService.getClassByClassNum(classNum);
    }


    // 教师详情
    @GetMapping("/getTeacher")
    public User getTeacher(@RequestParam String classNum){
        return classService.getTeacherByClassNum(classNum);
    }

    // 添加学生
    @PostMapping("/addStudent")
    public void addStudents(@RequestParam String classNum, @RequestParam Integer userId){
        List<Integer> stu = new ArrayList<>();
        stu.add(userId);
        classService.addStudents(stu, classNum);
    }

    // 添加学生
    @PostMapping("/addStudents")
    public void addStudents(@RequestParam String classNum, @RequestParam List<Integer> userIdList){
        classService.addStudents(userIdList, classNum);
    }

    // 移除学生
    @GetMapping("/removeStudents")
    public int removeStudents(@RequestParam List<Integer> stu, @RequestParam String classNum){
        return classService.removeStudents(stu, classNum);
    }

    // 查询课程（classNun, className）
    @GetMapping("/searchClass")
    public List<Class> searchClass(@RequestParam String query){
        int index = 0;
        Class aClass = null;
        List<Class> classList = new ArrayList<>();
        if(query == null){
            return classList;
        }
        for(index = 0; index < query.length(); index++){
            if(!Character.isDigit(query.charAt(index))){
                break;
            }
        }
        if(index < query.length()){
            classList = classService.getClassByClassname(query);
        } else {
            aClass = classService.getClassByClassNum(query);
            classList.add(aClass);
        }
        return classList;
    }

    @RequestMapping("/getClassDetail")
    public FullClass getClassDetail(@RequestParam String classNum){
        Class aclass = classService.getClassByClassNum(classNum);
        User teacher = classService.getTeacherByClassNum(classNum);
        FullClass fullClass = new FullClass();
        fullClass.set_class(aclass);
        fullClass.setTeacher(teacher);
        return fullClass;
    }

    // TODO 上传资源、课程、权限


    // TODO 搜索资源


    // TODO 资源列表


    // TODO 删除资源
}
