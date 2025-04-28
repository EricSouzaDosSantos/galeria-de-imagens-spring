package com.desafios.galeriaimagensspring.infrastructure.s3.exceptions.image;

public class S3DeletingException extends RuntimeException{

    public S3DeletingException(String message) {
        super(message);
    }
}
