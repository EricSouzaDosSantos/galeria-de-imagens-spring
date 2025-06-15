package com.desafios.galeriaimagensspring.application.usecase.image.save;

import com.desafios.galeriaimagensspring.core.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class SaveImageUseCaseImpl implements SaveImageUseCase {

    private final StorageService storageService;

    @Override
    public String execute(MultipartFile image, String folderName) {
        return storageService.upload(image, folderName);
    }
}
