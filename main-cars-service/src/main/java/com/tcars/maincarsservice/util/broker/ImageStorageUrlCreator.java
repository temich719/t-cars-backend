package com.tcars.maincarsservice.util.broker;

import com.tcars.maincarsservice.exception.SendImageToStorageException;
import com.tcars.maincarsservice.service.dto.broker.ImageMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ImageStorageUrlCreator {

    private static final String CAR_IMAGE_BUCKET_NAME = "cars-bucket";
    private static final String USER_AVATAR_BUCKET_NAME = "avatar-bucket";
    private static final String MIN_IO_ADDRESS = "http://minio-server:9000/";
    private static final String CAR_TO_STORAGE_CHANNEL = "outputChannel";
    private static final String AVATAR_TO_STORAGE_CHANNEL = "avatarOutputChannel";

    //object that helps us send data to broker (spring cloud feature to send info to destination)
    private final StreamBridge streamBridge;

    @Autowired
    public ImageStorageUrlCreator(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    //returns actual links to sent images
    public List<String> sendCarImagesToStorage(List<MultipartFile> carImages) throws SendImageToStorageException {
        return carImages.stream().map(file -> {
            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
            try {
                ImageMessage imageMessage = new ImageMessage(fileName, file.getBytes());
                sendImageToStorage(CAR_TO_STORAGE_CHANNEL, imageMessage);
                return MIN_IO_ADDRESS + CAR_IMAGE_BUCKET_NAME + "/" + fileName;
            } catch (IOException e) {
                throw new SendImageToStorageException("Can't send image (troubles with network or file access error)");
            }
        }).collect(Collectors.toList());
    }

    public String createAvatarUrl(MultipartFile avatar) throws SendImageToStorageException {
        try {
            String fileName = MIN_IO_ADDRESS + USER_AVATAR_BUCKET_NAME + "/" + avatar.getOriginalFilename();
            sendImageToStorage(AVATAR_TO_STORAGE_CHANNEL, new ImageMessage(fileName, avatar.getBytes()));
            return fileName;
        } catch (IOException e) {
            throw new SendImageToStorageException("Can't send image (troubles with network or file access error)");
        }
    }

    private void sendImageToStorage(String channel, ImageMessage imageMessage) {
        streamBridge.send(channel, imageMessage);
    }

}
