package com.example.bikeweb.service;

import com.example.bikeweb.Dao.UserDao;
import com.example.bikeweb.Dto.ChargeDto;
import com.example.bikeweb.staticfunc.Error;
import com.example.bikeweb.staticfunc.Resp;
import com.example.bikeweb.Dto.UserDto;
import com.example.bikeweb.entity.User;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserDao userDao;

    @Autowired
    ChargeService chargeService;

    /**
     * 用户注册，返回插入id
     * @param userDto
     * @return Resp<Long>
     * @throws Exception
     */
    public Resp<Long> registe(UserDto userDto){
        User user = User.convert(userDto);
        try {
            userDao.insert(user);
            return new Resp<Long>(user.getId());
        } catch (Exception e){
            logger.error(Error.Registe_FAIL.message);
            return new Resp<Long>(Error.Registe_FAIL);
        }
    }

    /**
     * 通过id寻找对应的User信息
     * @param id
     * @return UserDto
     * @throws Exception
     */
    public UserDto getUserById(Long id) throws Exception{
        User user = userDao.getUserById(id);
        UserDto userDto = UserDto.convert(user);
        return userDto;
    }

    /**
     * 通过phone寻找对应的User信息
     * @param phone
     * @return
     * @throws Exception
     */
    public UserDto getUserByPhone(String phone) throws Exception{
        User user = userDao.getUserByPhone(phone);
        UserDto userDto = UserDto.convert(user);
        return userDto;
    }

    /**
     * 更新用户信息
     * @param userDto
     * @return Resp<UserDto>
     */
    public Resp<UserDto> updateUser(UserDto userDto){
        User user = User.convert(userDto);
        try{
            userDao.updateUser(user);
            return new Resp<UserDto>(Error.Succ);
        } catch (Exception e){
            logger.error(Error.Update_FAIL.message,e);
            return new Resp<UserDto>(Error.Update_FAIL);
        }
    }

    /**
     * 获取健全的用户信息
     * @param userDto
     * @return UserDto
     */
    public UserDto getUserAll(UserDto userDto){
        User user = userDao.getUsedCount(userDto.getId());
        userDto.setUsednum(user.getUsedcount());
        userDto.setCostsum(user.getUsedcost());
        if(userDto.getCostsum() == null)
            userDto.setCostsum(0.00);
        return userDto;
    }

    @Transactional(rollbackFor = Exception.class)
    public void charge(UserDto userDto, ChargeDto chargeDto) throws Exception{
        userDto.setMoney(userDto.getMoney()+chargeDto.getDenomination().doubleValue());
        updateUser(userDto);
        try {
            chargeService.useCharge(chargeDto,userDto.getId());
        } catch (Exception e){
            throw e;
        }
    }
}
