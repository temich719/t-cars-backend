package com.tcars.objectstorageservice.service;

import com.tcars.objectstorageservice.dto.ImageMessage;
import com.tcars.objectstorageservice.exception.UploadImageToStorageException;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.function.Consumer;

@Service
public class ImageMinIoService {

    private final MinioClient minioClient;

    @Value("${minio.url}")
    private String minioUrl;
    @Value("${minio.access-key}")
    private String accessKey;
    @Value("${minio.secret-key}")
    private String secretKey;
    @Value("${minio.buckets.avatars}")
    private String avatarBucket;
    @Value("${minio.buckets.cars}")
    private String carsBucket;

    public ImageMinIoService() {
        this.minioClient = MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(accessKey, secretKey)
                .build();
    }

    @Bean
    public Consumer<ImageMessage> carImageInputChannel() {
        return imageMessage -> uploadImageToBucket(carsBucket, imageMessage);
    }

    @Bean
    public Consumer<ImageMessage> avatarImageInputChannel() {
        return imageMessage -> uploadImageToBucket(avatarBucket, imageMessage);
    }

    private void uploadImageToBucket(String bucketName, ImageMessage imageMessage) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(imageMessage.getFileName())
                            .stream(new ByteArrayInputStream(imageMessage.getBytesImage()), imageMessage.getBytesImage().length, -1)
                            .contentType("image/jpeg")
                            .build()
            );
        } catch (Exception e) {
            throw new UploadImageToStorageException("Can't save image to minio");
        }
    }
}
