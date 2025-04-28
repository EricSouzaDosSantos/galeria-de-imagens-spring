package com.desafios.galeriaimagensspring.usecase.image.save;

import com.desafios.galeriaimagensspring.domain.repository.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class SaveImageUseCaseImpl implements SaveImageUseCase {

    private final StorageService storageService;

    @Override
    public String execute(MultipartFile image) {
        return storageService.upload(image);
    }
}
