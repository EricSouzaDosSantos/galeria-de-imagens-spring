package com.desafios.galeriaimagensspring.domain.exception.user;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
