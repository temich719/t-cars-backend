package com.tcars.maincarsservice.util.mapper.impl;

import com.tcars.maincarsservice.dao.model.Car;
import com.tcars.maincarsservice.service.dto.CarDto;
import com.tcars.maincarsservice.util.mapper.Mapper;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class CarMapper implements Mapper<Car, CarDto> {

    @Override
    public Car mapToModel(CarDto carDto) {
        Car car = new Car(carDto.getName(), carDto.getPriceInUsd(), carDto.getDescription());
        if (nonNull(carDto.getImagePaths())) car.setImagePaths(carDto.getImagePaths());
        return car;
    }

    @Override
    public CarDto mapToDto(Car car) {
        CarDto carDto = new CarDto(car.getName(), car.getPriceInUsd(), car.getDescription());
        if (nonNull(car.getImagePaths())) carDto.setImagePaths(car.getImagePaths());
        return carDto;
    }
}
