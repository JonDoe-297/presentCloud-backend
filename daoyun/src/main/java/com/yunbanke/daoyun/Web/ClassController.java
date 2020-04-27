package com.yunbanke.daoyun.Web;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/class")
@CrossOrigin
@RestController
public class ClassController {
    // TODO addCLass
    @PostMapping("/addClass")
    public void addClass(@RequestParam String className, @RequestParam Integer userId){


    }
}
