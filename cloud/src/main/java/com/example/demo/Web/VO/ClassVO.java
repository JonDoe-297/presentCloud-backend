package com.example.demo.Web.VO;

import com.example.demo.infrastructure.DO.Class;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ClassVO {
    private String name;
    private String cno;
    private int number;

    public static ClassVO convertFromClass(Class c){
        ClassVO classVO = new ClassVO();
        BeanUtils.copyProperties(c, classVO);
        return classVO;
    }
}
