package com.example.bikeweb.controller;

import com.example.bikeweb.Dao.BikeDao;
import com.example.bikeweb.Dto.BikeDto;
import com.example.bikeweb.Dto.UserDto;
import com.example.bikeweb.service.BikeService;
import com.example.bikeweb.staticfunc.Error;
import com.example.bikeweb.staticfunc.Resp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeAccess extends BaseAccess{
    private static Logger logger = LoggerFactory.getLogger(UserAccess.class);

    @Resource
    BikeService bikeService;

    @RequestMapping("/get")
    public Resp<List<BikeDto>> getbike(HttpServletRequest req) throws Exception{
        return new Resp<>(Error.Succ,bikeService.getBikes());
    }

    @RequestMapping(value = "/fix", method = {RequestMethod.POST})
    public Resp<Long> fixbike(HttpServletRequest req, @RequestBody BikeDto bikeDto){
        BikeDto bikeDtoOld = bikeService.getBikeByNumber(bikeDto.getNumber());
        if(bikeDto == null)
            return new Resp<>(Error.Fail.code,"自行车不存在");
        else{
            bikeDto.setId(bikeDtoOld.getId());
            try {
                bikeService.updateBike(bikeDto);
            } catch (Exception e){
                return new Resp<>(Error.Fail.code,"更新失败");
            }
            return new Resp<>(Error.Succ);
        }
    }
}
