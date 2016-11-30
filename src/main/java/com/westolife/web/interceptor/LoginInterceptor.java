package com.westolife.web.interceptor;

import com.sun.security.jgss.AuthorizationDataEntry;
import com.westolife.web.controller.LoginController;
import com.westolife.web.exception.AuthorizeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by saigao on 16/8/7.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    private static final String[] IGNORE_URL = {"/login", "/signup", "/ueditor"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        for (String ignoreUrl : IGNORE_URL) {
            if (url.contains(ignoreUrl)) {
                return true;
            }
        }
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null || session.getAttribute("mail") == null) {
            logger.info("LoginInterceptor from url[%s]", url);
            throw new AuthorizeException(); // 抛出授权异常,在spring-mvc.xml设置重定向到/login页面
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception{
        super.postHandle(request, response, handler, mv);
    }
}
