package com.desafios.galeriaimagensspring.application.exception.album;

public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(String message) {
        super(message);
    }
}
