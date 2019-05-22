package com.example.bikeweb.service;

import com.example.bikeweb.Dao.ChargeDao;
import com.example.bikeweb.Dto.ChargeDto;
import com.example.bikeweb.entity.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChargeService {

    @Autowired
    ChargeDao chargeDao;

    public ChargeDto getChargeByNumber(String number) throws Exception{
        Charge charge = chargeDao.getChargeByNumber(number);
        ChargeDto chargeDto = ChargeDto.convert(charge);
        return chargeDto;
    }

    public void useCharge(ChargeDto chargeDto,Long uid) throws Exception{
        chargeDao.useCharge(chargeDto.getId(),uid);
    }
}
