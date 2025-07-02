package com.desafios.galeriaimagensspring.application.usecase.storage.upload;

import com.desafios.galeriaimagensspring.core.gateways.StorageGateway;
import org.springframework.web.multipart.MultipartFile;

public class UploadImageUseCaseImpl implements UploadImageUseCase {

    private final StorageGateway storageGateway;

    public UploadImageUseCaseImpl(StorageGateway storageGateway) {
        this.storageGateway = storageGateway;
    }

    @Override
    public String execute(MultipartFile multipartFile, String folderName) {
        return storageGateway.upload(multipartFile, folderName);
    }
}
