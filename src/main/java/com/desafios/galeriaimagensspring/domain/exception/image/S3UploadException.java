package com.desafios.galeriaimagensspring.domain.exception.image;

public class S3UploadException extends RuntimeException {

    public S3UploadException(String message) {
        super(message);
    }
}
