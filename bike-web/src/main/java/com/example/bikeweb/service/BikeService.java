package com.example.bikeweb.service;

import com.example.bikeweb.Dao.BikeDao;
import com.example.bikeweb.Dto.BikeDto;
import com.example.bikeweb.entity.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BikeService {

    @Autowired
    BikeDao bikeDao;

    public List<BikeDto> getBikes(){
        List<Bike> bikes = bikeDao.getBikes();
        List<BikeDto> bikeDtos = new ArrayList<>();
        for (Bike bike : bikes){
            BikeDto bikeDto = BikeDto.convert(bike);
            bikeDtos.add(bikeDto);
        }
        return bikeDtos;
    }

    public BikeDto getBikeByNumber(String number){
        Bike bike = bikeDao.getBikeByNumber(number);
        BikeDto bikeDto = BikeDto.convert(bike);
        return bikeDto;
    }

    public void updateBike(BikeDto bikeDto) throws Exception{
        Bike bike = Bike.convert(bikeDto);
        bikeDao.updateBike(bike);
    }
}
