package com.example.demo.infrastructure.DO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Class extends Base {
    private String name;
    private String cno;
    private int number;//班级人数
    @ManyToOne
    private User creator; // 班级教师

}
