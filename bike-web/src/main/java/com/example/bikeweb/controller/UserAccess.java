package com.example.bikeweb.controller;


import com.example.bikeweb.Dto.ChargeDto;
import com.example.bikeweb.entity.Charge;
import com.example.bikeweb.service.ChargeService;
import com.example.bikeweb.service.UserService;
import com.example.bikeweb.staticfunc.Error;
import com.example.bikeweb.staticfunc.Resp;
import com.example.bikeweb.Dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/hom")
public class UserAccess extends BaseAccess{
    private static Logger logger = LoggerFactory.getLogger(UserAccess.class);

    @Resource
    ChargeService chargeService;

    /** 用户注册
     * @param userDto
     * @return Resp<Long>
     * @throws
     */
    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public Resp<Long> register(HttpServletRequest req, @RequestBody UserDto userDto) throws Exception{
        logger.info(userDto.toString());
        Resp<Long> resp = userService.registe(userDto);
        if(resp.errorCode != 0)
            return new Resp<>(resp.errorCode,resp.message);
        return resp;
    }

    /**
     * 登陆
     * @param req
     * @param userDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public Resp<UserDto> login(HttpServletRequest req, @RequestBody UserDto userDto) throws Exception{
        logger.error(userDto.toString());
        HttpSession session = req.getSession(false);
        if(session!=null && checkUser(req) != null) {
                return new Resp<>(Error.Succ.code,"已登陆");
        } else{
            UserDto userDtoOld = new UserDto();
            userDtoOld = userService.getUserByPhone(userDto.getPhone());
            if(userDtoOld == null)
                return new Resp<>(Error.Login_FAIL.code,"用户不存在");
            if (userDtoOld.getPassword().equals(userDto.getPassword()) && userDtoOld.getPhone().equals(userDto.getPhone())){
                session = req.getSession();
                session.setAttribute("id",userDtoOld.getId());
                session.setAttribute("phone",userDtoOld.getPhone());
                session.setAttribute("role",userDtoOld.getPermission());
                return new Resp<>(Error.Succ);
            }
            else{
                logger.error("密码不正确");
                return new Resp<UserDto>(Error.Login_FAIL.code, "密码不正确");
            }
        }
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.POST})
    public Resp<Long> logout(HttpServletRequest req) throws Exception{
        HttpSession session = req.getSession(false);
        session.invalidate();
        return new Resp<>(Error.Succ.code,"退出成功");
    }

    /**
     * 用户信息更新,密码更新
     * @param req
     * @param userDto
     * @return Resp<UserDto>
     */
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Resp<UserDto> update(HttpServletRequest req, @RequestBody UserDto userDto){
        logger.info(userDto.toString());
        return userService.updateUser(userDto);
    }

    /**
     * 对已登陆用户的信息获取
     * @param req
     * @return
     */
    @RequestMapping("/get")
    public Resp<UserDto> getuser(HttpServletRequest req){
        try{
            UserDto userDto = checkUser(req);
            if(userDto == null) {
                logger.info(Error.NoLogin.message);
                return new Resp<>(Error.NoLogin);
            }
            userDto = userService.getUserAll(userDto);
            return new Resp<>(userDto);
        } catch (Exception e){
            logger.info(Error.Fail.message,e);
            return new Resp<>(Error.Fail);
        }
    }

    /**
     * 获取充值卡
     * @param req
     * @param chargeDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/charge", method = {RequestMethod.POST})
    public Resp<Long> charge(HttpServletRequest req, @RequestBody ChargeDto chargeDto) throws Exception{
        if(chargeDto.getNumber() == null || chargeDto.getPassword() == null)
            return new Resp<>(Error.Fail.code, "卡号和密码不能为空");
        ChargeDto chargeDtoOld = chargeService.getChargeByNumber(chargeDto.getNumber());
        if(chargeDtoOld == null)
            return new Resp<>(Error.Fail.code, "卡号不存在");
        if(!chargeDtoOld.getPassword().equals(chargeDto.getPassword()))
            return new Resp<>(Error.Fail.code, "密码错误");
        if(chargeDtoOld.getValidity() == 1)
            return new Resp<>(Error.Fail.code, "充值卡已使用");

        UserDto userDto = checkUser(req);
        try{
            userService.charge(userDto,chargeDtoOld);
            return new Resp<>(Error.Succ);
        } catch (Exception e){
            return new Resp<>(Error.Fail);
        }
    }
}

