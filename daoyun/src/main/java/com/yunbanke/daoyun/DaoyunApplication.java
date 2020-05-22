package com.yunbanke.daoyun;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableSwagger2Doc
@SpringBootApplication
@ServletComponentScan
public class DaoyunApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaoyunApplication.class, args);
    }

}
