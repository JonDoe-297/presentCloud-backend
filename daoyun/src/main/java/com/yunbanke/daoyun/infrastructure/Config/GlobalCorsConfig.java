//package com.yunbanke.daoyun.infrastructure.Config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
////全局的跨域访问设置
//@Configuration
//public class GlobalCorsConfig {
//    @Bean
//    public WebMvcConfigurer configurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowCredentials(true)
//                        .allowedMethods("*")
//                        .allowedHeaders("*")
//                        .exposedHeaders("Access-control-Allow-Origin", "Access-Control-Allow-Headers", "Access-Control-Expose-Headers");
//            }
//        };
//    }
//}
