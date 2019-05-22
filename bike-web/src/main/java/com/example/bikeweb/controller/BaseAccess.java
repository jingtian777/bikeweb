package com.example.bikeweb.controller;

import com.example.bikeweb.Dto.UserDto;
import com.example.bikeweb.entity.User;
import com.example.bikeweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseAccess {
    private static Logger logger = LoggerFactory.getLogger(BaseAccess.class);

    @Autowired
    protected UserService userService;

    /**
     * 通过req返回用户信息
     * @param req
     * @return UserDto
     * @throws Exception
     */
    public UserDto checkUser(HttpServletRequest req) throws Exception{
        HttpSession session = req.getSession(false);
        if(session == null)
            return null;
        Long id = session.getAttribute("id") == null ? 0L : Long.valueOf(session.getAttribute("id").toString());
        return userService.getUserById(id);
    }
}
