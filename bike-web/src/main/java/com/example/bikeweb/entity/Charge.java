package com.example.bikeweb.entity;

import com.example.bikeweb.Dto.ChargeDto;
import lombok.Data;

@Data
public class Charge extends Base{
    Charge(){}
    Charge(ChargeDto chargeDto){
        id = chargeDto.getId();
        number = chargeDto.getNumber();
        denomination = chargeDto.getDenomination();
        validity = chargeDto.getValidity();
        userId = chargeDto.getUserId();
    }
    String number;
    String password;
    Integer denomination;
    // 有效性(0=有效,1=失效)
    Integer validity;
    Integer userId;

    public static Charge convert(ChargeDto chargeDto){
        if(chargeDto == null)
            return null;
        return new Charge(chargeDto);
    }
}