package com.example.bikeweb.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HavingLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session != null) {
            Long id = session.getAttribute("id") == null ? 0L : Long.valueOf(session.getAttribute("id").toString());
            if(id != 0) {
                response.sendRedirect("/bike-web/base_pages_profile.html");
                return false;
            } else{
                return true;
            }
        }
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
