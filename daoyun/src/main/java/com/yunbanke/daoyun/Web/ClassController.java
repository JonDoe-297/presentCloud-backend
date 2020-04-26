package com.yunbanke.daoyun.Web;

import com.yunbanke.daoyun.Domain.entity.Class;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RequestMapping("/class")
@CrossOrigin
@RestController
public class ClassController {
    // TODO addCLass
    @PostMapping("/addClass")
    public void addClass(@RequestParam String className, @RequestParam Integer userId){


    }
}
