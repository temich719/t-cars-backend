package com.tcars.maincarsservice.service;

import com.tcars.maincarsservice.exception.NotFoundException;
import com.tcars.maincarsservice.service.dto.CarDto;

import java.util.List;
import java.util.UUID;

public interface CarService {

    List<CarDto> getAllCars(int page, int size);

    CarDto getCarByUuid(UUID uuid) throws NotFoundException;

    void createCar(CarDto carDto);

    void updateCarByUuid(UUID uuid, CarDto carDto) throws NotFoundException;

    void deleteCarByUuid(UUID uuid) throws NotFoundException;

}
