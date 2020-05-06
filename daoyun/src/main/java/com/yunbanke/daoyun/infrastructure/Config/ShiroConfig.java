package com.yunbanke.daoyun.infrastructure.Config;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //用shiro的filter拦截器
    @Bean(name = "shiroFilterFactoryBean")
    ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        shiroFilterFactoryBean.setLoginUrl("/toLogin");//登录的页面
        shiroFilterFactoryBean.setSuccessUrl("/index");//登录成功的页面
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /*
            anon表示所有人都可以访问
            authc表示用户必须认证过才能访问
         */
//        filterChainDefinitionMap.put("/static/**", "anon"); //静态资源所有人都可以访问，这样前端的css，JavaScript等代码才能被访问到
//        filterChainDefinitionMap.put("/login", "anon"); //登录url所有人都可以访问
//        filterChainDefinitionMap.put("/toRegister", "anon");
//        filterChainDefinitionMap.put("/register", "anon");
//        //filterChainDefinitionMap.put("/teacher/createClass", "authc");
//        filterChainDefinitionMap.put("/logout", "logout");//登出url，登出操作shiro已经写好，只需要在前端跳转到该url
//        filterChainDefinitionMap.put("/**", "authc"); //剩下的所有页面都需要认证过才能访问。这一行必须放在最后，放在前面所有的资源都被拦截了
        filterChainDefinitionMap.put("/**", "anon");

        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(3);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }
}
