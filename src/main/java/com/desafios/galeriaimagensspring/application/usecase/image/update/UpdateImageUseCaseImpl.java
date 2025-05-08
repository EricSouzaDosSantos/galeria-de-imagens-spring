package com.desafios.galeriaimagensspring.application.usecase.image.update;

import com.desafios.galeriaimagensspring.infrastructure.s3.storage.S3StorageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UpdateImageUseCaseImpl implements UpdateImageUseCase {

    private final S3StorageServiceImpl s3StorageService;

    @Override
    public String execute(String oldImageURL, MultipartFile multipartFile) {
        return s3StorageService.updateImage(oldImageURL, multipartFile);
    }
}
