package com.desafios.galeriaimagensspring.application.usecase.image.delete;

import com.desafios.galeriaimagensspring.core.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteImageUseCaseImpl implements DeleteImageUseCase {

    private final StorageService storageService;

    @Override
    public void execute(String imageURL) {
        storageService.delete(imageURL);
    }
}
