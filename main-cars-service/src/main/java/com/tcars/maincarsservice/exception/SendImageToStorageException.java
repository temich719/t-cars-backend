package com.tcars.maincarsservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendImageToStorageException extends RuntimeException {

    private String message;

    public SendImageToStorageException(String message) {
        super(message);
        this.message = message;
    }
}
