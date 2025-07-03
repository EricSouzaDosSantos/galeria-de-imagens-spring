package com.desafios.galeriaimagensspring.infrastructure.configuration.beans.image;

import com.desafios.galeriaimagensspring.application.usecase.image.delete.DeleteImageUseCase;
import com.desafios.galeriaimagensspring.application.usecase.image.delete.DeleteImageUseCaseImpl;
import com.desafios.galeriaimagensspring.application.usecase.image.get.GetImageUseCase;
import com.desafios.galeriaimagensspring.application.usecase.image.get.GetImageUseCaseImpl;
import com.desafios.galeriaimagensspring.application.usecase.image.save.SaveImageUseCase;
import com.desafios.galeriaimagensspring.application.usecase.image.save.SaveImageUseCaseImpl;
import com.desafios.galeriaimagensspring.application.usecase.image.update.UpdateImageUseCase;
import com.desafios.galeriaimagensspring.application.usecase.image.update.UpdateImageUseCaseImpl;
import com.desafios.galeriaimagensspring.core.gateways.ImageGateway;
import com.desafios.galeriaimagensspring.core.gateways.StorageGateway;
import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.image.JpaImageRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageUseCaseConfig {
    @Bean
    public SaveImageUseCase saveImageUseCase(JpaImageRepository imageRepository) {
        return new SaveImageUseCaseImpl(imageRepository);
    }

    @Bean
    public DeleteImageUseCase deleteImageUseCase(StorageGateway storageGateway,
                                                 ImageGateway imageGateway) {
        return new DeleteImageUseCaseImpl(storageGateway,
                imageGateway);
    }

    @Bean
    public GetImageUseCase getImageUseCase(ImageGateway imageGateway) {
        return new GetImageUseCaseImpl(imageGateway);
    }

    @Bean
    public UpdateImageUseCase updateImageUseCase(StorageGateway storageGateway,
                                                 ImageGateway imageGateway) {
        return new UpdateImageUseCaseImpl(storageGateway,
                imageGateway);
    }
}
