package com.desafios.galeriaimagensspring.core.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String upload(MultipartFile file, String folderName);
    void delete(String url);
    void createUserFolder(String folderName);
    String updateImage(String oldImageURL, MultipartFile multipartFile);
}

