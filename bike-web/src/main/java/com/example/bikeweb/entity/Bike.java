package com.example.bikeweb.entity;

import com.example.bikeweb.Dto.BikeDto;
import lombok.Data;

@Data
public class Bike extends Base{
    Bike(){}
    Bike(BikeDto bikeDto){
        id = bikeDto.getId();
        type = bikeDto.getType();
        gps = bikeDto.getGps();
        status = bikeDto.getStatus();
        number = bikeDto.getNumber();
    }
    private String type;
    private String gps;
    private Integer status;
    private String number;

    public static Bike convert(BikeDto bikeDto){
        if(bikeDto == null)
            return null;
        return new Bike(bikeDto);
    }
}
