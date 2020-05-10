package com.yunbanke.daoyun.Service;



import com.yunbanke.daoyun.infrastructure.Persistence.ClassRepository;
import com.yunbanke.daoyun.infrastructure.Persistence.UserRepository;
import com.yunbanke.daoyun.infrastructure.entity.Class;
import com.yunbanke.daoyun.infrastructure.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private UserRepository userRepository;

    public int addClass(String className, Integer userId){
        Class c = new Class();
        c.setClassname(className);
        c.setUserid(userId);
        Random r = new Random();
        c.setClassnum(""+r.nextInt(10000000));
        List<User> stu = new ArrayList<>();
        c.setStudentList(stu);
        classRepository.save(c);
        return 1;
    }

    // 通过课程号获得课程
    public Class getClassByClassNum(String classNum){
        List<Class> classes = classRepository.getClassesByClassnum(classNum);
        if(classes.size() == 1){
            return classes.get(0);
        }
        else if(classes.size() > 1){
            System.out.println("ClassService/getClassByClassBum-------> There are one more classes which classNum is " + classNum);
            return classes.get(0);
        }
        else {
            System.out.println("ClassService/getClassByClassBum-------> There is no class which classNum is " + classNum);
            return new Class();
        }
    }

    // 添加学生
    public int addStudents(List<Integer> stu, String classNum){
        Class c = getClassByClassNum(classNum);
        if(c.getClassid() == null){
            return 9999;
        } else {
            List<User> students = new ArrayList<>();
            for(int i = 0; i < stu.size(); i++){
                List<User> _student = userRepository.getUsersByUserid(stu.get(i));
                students.add(_student.get(0));
            }
            c.setStudentList(students);
            classRepository.save(c);
            System.out.println(students);
        }
        return 1;
    }

    //
    public User getTeacherByClassNum(String classNum){
        Class c = getClassByClassNum(classNum);
        List<User> teacherList = userRepository.getUsersByUserid(c.getUserid());
        return teacherList.get(0);
    }

    public int removeStudents(List<Integer> stu, String classNum){
        Class c = getClassByClassNum(classNum);
        List<User> students = c.getStudentList();
        for(int i = 0; i < 0; i++){
            for(int j = 0; j < students.size(); j++){
                if(students.get(j).getUserid() == stu.get(i)){
                    students.remove(j);
                }
            }
        }
        c.setStudentList(students);
        classRepository.save(c);
        return 1;
    }

    public List<Class> getClassByClassname(String className){
        return classRepository.getClassesByClassname(className);
    }
    // 查询课程
}
