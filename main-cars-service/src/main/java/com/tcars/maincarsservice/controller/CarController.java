package com.tcars.maincarsservice.controller;

import com.tcars.maincarsservice.exception.NotFoundException;
import com.tcars.maincarsservice.service.CarService;
import com.tcars.maincarsservice.service.dto.CarDto;
import com.tcars.maincarsservice.util.broker.CarImageUrlConstructor;
import com.tcars.maincarsservice.util.broker.ImageStorageUrlCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;
    private final ImageStorageUrlCreator imageStorageUrlCreator;

    @Autowired
    public CarController(CarService carService, ImageStorageUrlCreator imageStorageUrlCreator) {
        this.carService = carService;
        this.imageStorageUrlCreator = imageStorageUrlCreator;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> getAllCars(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int size) {
        return carService.getAllCars(page, size);
    }

    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CarDto getCarByUuid(@PathVariable UUID uuid) throws NotFoundException {
        return carService.getCarByUuid(uuid);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCar(@RequestPart("car") CarDto carDto, @RequestPart("images") List<MultipartFile> images) {
        List<String> filesUrlsInStorage = imageStorageUrlCreator.sendCarImagesToStorage(images);
        carDto.setImagePaths(CarImageUrlConstructor.constructUrl(filesUrlsInStorage));
        carService.createCar(carDto);
    }

    @PatchMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateCarByUuid(@PathVariable UUID uuid, @RequestBody CarDto carDto) throws NotFoundException {
        carService.updateCarByUuid(uuid, carDto);
    }

    @DeleteMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCarByUuid(@PathVariable UUID uuid) throws NotFoundException {
        carService.deleteCarByUuid(uuid);
    }
}
