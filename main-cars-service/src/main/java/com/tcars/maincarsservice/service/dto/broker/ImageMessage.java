package com.tcars.maincarsservice.service.dto.broker;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageMessage {

    private String fileName;
    private byte[] bytesImage;

}
