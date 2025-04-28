package com.desafios.galeriaimagensspring.domain.repository;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String upload(MultipartFile file);
    void delete(String url);
    void createUserFolder(String folderName);
    String updateImage(String oldImageURL, MultipartFile multipartFile);
}

