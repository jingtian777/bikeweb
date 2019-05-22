package com.example.bikeweb.Dto;

import com.example.bikeweb.entity.Bike;
import lombok.Data;

import java.io.Serializable;

@Data
public class BikeDto implements Serializable {
    BikeDto(){}
    BikeDto(Bike bike){
        id = bike.getId();
        type = bike.getType();
        gps = bike.getGps();
        status = bike.getStatus();
        number = bike.getNumber();
    }
    private Long id;
    private String type;
    private String gps;
    private Integer status;
    private String number;

    public static BikeDto convert(Bike bike){
        if(bike == null)
            return null;
        return new BikeDto(bike);
    }
}
