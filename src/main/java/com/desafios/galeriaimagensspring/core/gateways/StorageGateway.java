package com.desafios.galeriaimagensspring.core.gateways;

import com.desafios.galeriaimagensspring.core.model.FileData;

public interface StorageGateway {
    String upload(FileData file, String folderName);
    void delete(String url);
    void createUserFolder(String email);
    String updateImage(String oldImageURL, FileData file);
}

