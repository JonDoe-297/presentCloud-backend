package com.yunbanke.daoyun.Service;



import com.yunbanke.daoyun.infrastructure.Persistence.ClassRepository;
import com.yunbanke.daoyun.infrastructure.Persistence.UserRepository;
import com.yunbanke.daoyun.Web.VO.RetResponse;
import com.yunbanke.daoyun.infrastructure.entity.Class;
import com.yunbanke.daoyun.infrastructure.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(ClassService.class);

    public RetResponse addClass(String className, Integer userId){
        Class c = new Class();
        c.setClassname(className);
        List<User> userList = userRepository.getUsersByUserid(userId);
        if(userList.size() == 0){
            return new RetResponse("2001", "用户id（" + userId + "）未找到");
        }
        c.setUserid(userId);
        Random r = new Random();
        c.setClassnum(""+r.nextInt(100000000));
        List<User> stu = new ArrayList<>();
        c.setStudentList(stu);
        return new RetResponse(classRepository.save(c));
    }

    // 通过课程号获得课程
    public RetResponse getClassByClassNum(String classNum){
        List<Class> classes = classRepository.getClassesByClassnum(classNum);
        if(classes.size() == 1){
            return new RetResponse(classes.get(0));
        }
        else if(classes.size() > 1){
            logger.warn("There are one more classes which classNum is " + classNum);
            return new RetResponse("2002", "查询到大于一个课程");
        }
        else {
            logger.warn("There is no class which classNum is " + classNum);
            return new RetResponse("2001", "未查询到课程");
        }
    }

    // 添加学生
    public RetResponse addStudents(List<Integer> stu, String classNum){
        RetResponse classRet = getClassByClassNum(classNum);
        List<Integer> failure = new ArrayList<>();
        if(classRet.getCode().equals("2001") || classRet.getCode().equals("2002")){
            return classRet;
        } else {
            Class c = (Class)classRet.getData();
            List<User> students = new ArrayList<>();
            for(int i = 0; i < stu.size(); i++){
                List<User> _student = userRepository.getUsersByUserid(stu.get(i));
                if(_student.isEmpty()){
                    failure.add(stu.get(i));
                }else {
                    students.add(_student.get(0));
                }
            }
            c.setStudentList(students);
            if(classRepository.save(c) != null) {
                if(failure.size() == 0){
                    return new RetResponse();
                } else {
                    return new RetResponse("2002", "下列未添加成功", failure);
                }
            } else {
                logger.error("classRepository存储错误.");
                return new RetResponse("5001", "数据库存储出错");
            }
        }
    }

    //
    public RetResponse getTeacherByClassNum(String classNum){
        RetResponse classRet = getClassByClassNum(classNum);
        if(!classRet.getCode().equals("200")){
            return classRet;
        }
        Class c = (Class) classRet.getData();
        List<User> teacherList = userRepository.getUsersByUserid(c.getUserid());
        if(teacherList.isEmpty()){
            return new RetResponse("2001", "未找到教师");
        }
        return new RetResponse(teacherList.get(0));
    }

    public RetResponse removeStudents(List<Integer> stuDel, String classNum){
        RetResponse classRet = getClassByClassNum(classNum);
        if(!classRet.getCode().equals("200")){
            return classRet;
        }
        Class c = (Class) classRet.getData();
        List<User> students = c.getStudentList();
        Iterator<User> iterator = students.iterator();
        while(iterator.hasNext()){
            for(int i = 0; i < stuDel.size(); i++){
                if(iterator.next().getUserid() == stuDel.get(i)){
                    iterator.remove();
                }
            }
        }
        c.setStudentList(students);
        System.out.println(classRepository.save(c));
        if(classRepository.save(c) != null) {
            return new RetResponse();
        } else {
            logger.error("classRepository存储错误.");
            return new RetResponse("5001", "数据库存储错误");
        }
//        return 1;
    }

    public List<Class> getClassByClassname(String className){
        return classRepository.getClassesByClassname(className);
    }
    // 查询课程
}
