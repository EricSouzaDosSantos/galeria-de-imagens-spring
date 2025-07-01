package com.desafios.galeriaimagensspring.core.gateways;

import org.springframework.web.multipart.MultipartFile;

public interface StorageGateway {
    String upload(MultipartFile file, String folderName);
    void delete(String url);
    void createUserFolder(String email);
    String updateImage(String oldImageURL, MultipartFile file);
}

