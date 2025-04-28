package com.desafios.galeriaimagensspring.infrastructure.s3.exceptions.image;

public class S3UploadException extends RuntimeException {

    public S3UploadException(String message) {
        super(message);
    }
}
