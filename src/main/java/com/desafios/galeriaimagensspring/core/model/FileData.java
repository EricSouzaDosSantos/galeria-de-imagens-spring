package com.desafios.galeriaimagensspring.core.model;

import java.io.InputStream;

public record FileData(
    String fileName,
    byte[] content,
    String contentType,
    InputStream inputStream
) {
}
