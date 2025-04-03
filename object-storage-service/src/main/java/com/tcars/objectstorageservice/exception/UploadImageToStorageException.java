package com.tcars.objectstorageservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadImageToStorageException extends RuntimeException{

    private final String message;

    public UploadImageToStorageException(String message) {
        super(message);
        this.message = message;
    }

}
