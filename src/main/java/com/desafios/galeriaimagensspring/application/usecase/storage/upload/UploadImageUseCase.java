package com.desafios.galeriaimagensspring.application.usecase.storage.upload;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImageUseCase {
    String execute(MultipartFile multipartFile, String folderName);
}
