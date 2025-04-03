package com.tcars.maincarsservice.service.impl;

import com.tcars.maincarsservice.dao.model.Car;
import com.tcars.maincarsservice.dao.repository.CarDAO;
import com.tcars.maincarsservice.exception.NotFoundException;
import com.tcars.maincarsservice.service.CarService;
import com.tcars.maincarsservice.service.dto.CarDto;
import com.tcars.maincarsservice.util.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarDAO carDAO;
    private final Mapper<Car, CarDto> carMapper;

    @Autowired
    public CarServiceImpl(CarDAO carDAO, Mapper<Car, CarDto> carMapper) {
        this.carDAO = carDAO;
        this.carMapper = carMapper;
    }

    @Override
    public List<CarDto> getAllCars(int page, int size) {
        return carDAO.findAll(PageRequest.of(page, size)).stream().map(carMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CarDto getCarByUuid(UUID uuid) throws NotFoundException {
        return carMapper.mapToDto(findCarByUuid(uuid));
    }

    @Override
    public void createCar(CarDto carDto) {
        carDAO.save(carMapper.mapToModel(carDto));
    }

    @Override
    public void updateCarByUuid(UUID uuid, CarDto carDto) throws NotFoundException {
        Car carForUpdate = findCarByUuid(uuid);
        carForUpdate.setName(carDto.getName());
        carForUpdate.setDescription(carDto.getDescription());
        carForUpdate.setPriceInUsd(carDto.getPriceInUsd());
        carDAO.save(carForUpdate);
    }

    @Override
    public void deleteCarByUuid(UUID uuid) throws NotFoundException {
        carDAO.delete(findCarByUuid(uuid));
    }

    private Car findCarByUuid(UUID uuid) throws NotFoundException {
        return carDAO.findById(uuid)
                .orElseThrow(
                        () -> new NotFoundException("Car with uuid = " + uuid + "not found")
                );
    }
}
