package com.desafios.galeriaimagensspring.domain.exception.image;

public class S3DeletingException extends RuntimeException{

    public S3DeletingException(String message) {
        super(message);
    }
}
