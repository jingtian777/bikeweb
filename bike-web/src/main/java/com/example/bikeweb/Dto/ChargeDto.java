package com.example.bikeweb.Dto;

import com.example.bikeweb.entity.Charge;
import lombok.Data;

import java.io.Serializable;

@Data
public class ChargeDto implements Serializable {
    ChargeDto(){}
    ChargeDto(Charge charge){
        id = charge.getId();
        number = charge.getNumber();
        password = charge.getPassword();
        denomination = charge.getDenomination();
        validity = charge.getValidity();
        userId = charge.getUserId();
    }

    private Long id;
    String number;
    String password;
    Integer denomination;
    // 有效性(0=有效,1=失效)
    Integer validity;
    Integer userId;

    public static ChargeDto convert(Charge charge){
        if(charge == null)
            return null;
        return new ChargeDto(charge);
    }
}
