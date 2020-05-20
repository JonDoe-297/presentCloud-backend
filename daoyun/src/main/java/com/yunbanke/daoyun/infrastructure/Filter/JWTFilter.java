package com.yunbanke.daoyun.infrastructure.Filter;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.yunbanke.daoyun.infrastructure.Util.common.JsonConvertUtil;
import com.yunbanke.daoyun.infrastructure.Util.common.ResponseBean;
import com.yunbanke.daoyun.infrastructure.exception.CustomException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

public class JWTFilter extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (this.isLoginAttempt(request, response)) {
            try {
                this.executeLogin(request, response);
                return true;
            } catch (Exception e) {
                String msg = e.getMessage();
                Throwable throwable = e.getCause();
                if (throwable instanceof SignatureVerificationException) {
                    // 该异常为JWT的AccessToken认证失败(Token或者密钥不正确)
                    msg = "Token或者密钥不正确(" + throwable.getMessage() + ")";
                } else if (throwable instanceof TokenExpiredException) {
                    // 该异常为JWT的AccessToken已过期，判断RefreshToken未过期就进行AccessToken刷新

                    msg = "Token已过期(" + throwable.getMessage() + ")";

                } else {
                    // 应用异常不为空
                    if (throwable != null) {
                        // 获取应用异常msg
                        msg = throwable.getMessage();
                    }
                }
                this.response401(response, msg);
                return false;
            }
        } else {
            // 没有携带Token
            HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
            // 获取当前请求类型
            String httpMethod = httpServletRequest.getMethod();
            // 获取当前请求URI
            String requestURI = httpServletRequest.getRequestURI();
            //logger.info("当前请求 {} Authorization属性(Token)为空 请求类型 {}", requestURI, httpMethod);
            // mustLoginFlag = true 开启任何请求必须登录才可访问
            final Boolean mustLoginFlag = false;
            if (mustLoginFlag) {
                this.response401(response, "请先登录");
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        this.sendChallenge(request, response);
        return false;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        String token = this.getAuthzHeader(request);
        return token != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        // 拿到当前Header中Authorization的AccessToken(Shiro中getAuthzHeader方法已经实现)
        JwtToken token = new JwtToken(this.getAuthzHeader(request));
        // 提交给UserRealm进行认证，如果错误他会抛出异常并被捕获
        this.getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
//        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
//            httpServletResponse.setStatus(HttpStatus.OK.value());
//            return false;
//        }
        return super.preHandle(request, response);
    }

    /**
     * 无需转发，直接返回Response信息
     */
    private void response401(ServletResponse response, String msg) {
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = httpServletResponse.getWriter()) {
            String data = JsonConvertUtil.objectToJson(new ResponseBean(HttpStatus.UNAUTHORIZED.value(), "无权访问(Unauthorized):" + msg, null));
            out.append(data);
        } catch (IOException e) {
            //logger.error("直接返回Response信息出现IOException异常:{}", e.getMessage());
            throw new CustomException("直接返回Response信息出现IOException异常:" + e.getMessage());
        }
    }
}
