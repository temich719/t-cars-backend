package com.tcars.maincarsservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private String name;
    private String priceInUsd;
    private String description;
    private String imagePaths;

    public CarDto(String name, String priceInUsd, String description) {
        this.name = name;
        this.priceInUsd = priceInUsd;
        this.description = description;
    }
}
