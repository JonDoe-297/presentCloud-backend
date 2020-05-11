package com.yunbanke.daoyun.Web;


import com.yunbanke.daoyun.Service.ClassService;
import com.yunbanke.daoyun.infrastructure.Types.FullClass;
import com.yunbanke.daoyun.infrastructure.Types.RetResponse;
import com.yunbanke.daoyun.infrastructure.entity.Class;
import com.yunbanke.daoyun.infrastructure.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public RetResponse addClass(@RequestParam String className, @RequestParam Integer userId){
        return classService.addClass(className, userId);
    }



    // 课程详情
    @GetMapping("/getClassByClassnum")
    public RetResponse<Class> getClassByClassnum(@RequestParam String classNum){
        return classService.getClassByClassNum(classNum);
    }


    // 教师详情
    @GetMapping("/getTeacher")
    public RetResponse getTeacher(@RequestParam String classNum){
        return classService.getTeacherByClassNum(classNum);
    }

    // 添加学生
    @PostMapping("/addStudent")
    public RetResponse addStudents(@RequestParam String classNum, @RequestParam Integer userId){
        List<Integer> stu = new ArrayList<>();
        stu.add(userId);
        return classService.addStudents(stu, classNum);
    }

    // 添加学生
    @PostMapping("/addStudents")
    public RetResponse addStudents(@RequestParam String classNum, @RequestParam List<Integer> userIdList){
        return classService.addStudents(userIdList, classNum);
    }

    // 移除学生
    @GetMapping("/removeStudent")
    public RetResponse removeStudent(@RequestParam String classNum, @RequestParam Integer stuId){
        List<Integer> stu = new ArrayList<>();
        stu.add(stuId);
        return classService.removeStudents(stu, classNum);
    }

    // 移除学生
    @GetMapping("/removeStudents")
    public RetResponse removeStudents(@RequestParam String classNum, @RequestParam List<Integer> stu){
        return classService.removeStudents(stu, classNum);
    }



    // 查询课程（classNun, className）
    @GetMapping("/searchClass")
    public RetResponse searchClass(@RequestParam String query){
        int index = 0;
        List<Class> classList = new ArrayList<>();
        if(query == null){
            return new RetResponse("2002", "查询不可为空");
        }
        for(index = 0; index < query.length(); index++){
            if(!Character.isDigit(query.charAt(index))){
                break;
            }
        }
        // 按照课程名查询
        if(index < query.length()){
            classList = classService.getClassByClassname(query);
        } else { // 按照课程号查询
            RetResponse classRet = classService.getClassByClassNum(query);
            if(classRet.getCode().equals("200")){
                classList.add((Class)classRet.getData());
            } else {
                return classRet;
            }
        }
        return new RetResponse(classList);
    }

    @RequestMapping("/getClassDetail")
    public RetResponse getClassDetail(@RequestParam String classNum){
        Class c = new Class();
        User teacher = new User();
        if(classNum == null){
            return new RetResponse("2002", "查询不可为空");
        }
        RetResponse classRet = classService.getClassByClassNum(classNum);
        if(classRet.getCode().equals("200")){
            c = (Class)classRet.getData();
        } else {
            return classRet;
        }
        RetResponse teacherRet = classService.getTeacherByClassNum(classNum);
        if(teacherRet.getCode().equals("200")){
            teacher = (User) teacherRet.getData();
        } else {
            return teacherRet;
        }
        FullClass fullClass = new FullClass();
        fullClass.set_class(c);
        fullClass.setTeacher(teacher);
        return new RetResponse(fullClass);
    }

    // TODO 上传资源、课程、权限


    // TODO 搜索资源


    // TODO 资源列表


    // TODO 删除资源
}
