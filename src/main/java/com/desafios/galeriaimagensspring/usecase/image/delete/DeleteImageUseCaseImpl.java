package com.desafios.galeriaimagensspring.usecase.image.delete;

import com.desafios.galeriaimagensspring.domain.repository.ImagensRepository;
import com.desafios.galeriaimagensspring.domain.repository.StorageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@RequiredArgsConstructor
public class DeleteImageUseCaseImpl implements DeleteImageUseCase {

    private final StorageService storageService;

    @Override
    public void execute(String imageURL) {
        storageService.delete(imageURL);
    }
}
