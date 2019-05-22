package com.example.bikeweb.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 迷宫的中心
 * @date 2019/3/28 13:26
 */
public class LoginCheckInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session != null) {
            Long id = session.getAttribute("id") == null ? 0L : Long.valueOf(session.getAttribute("id").toString());
            if(id != 0){
                return true;
            } else {
                response.sendRedirect("/bike-web/base_pages_login.html");
                return false;
            }
        }
        response.sendRedirect("/bike-web/base_pages_login.html");
        return false;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
