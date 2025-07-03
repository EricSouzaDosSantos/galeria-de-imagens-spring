package com.desafios.galeriaimagensspring.infrastructure.configuration.beans.image;

import com.desafios.galeriaimagensspring.application.service.ImagemService;
import com.desafios.galeriaimagensspring.application.usecase.auth.get.GetAuthenticateUserUseCase;
import com.desafios.galeriaimagensspring.application.usecase.auth.validation.ValidateUserAuthorizationUseCase;
import com.desafios.galeriaimagensspring.application.usecase.image.delete.DeleteImageUseCase;
import com.desafios.galeriaimagensspring.application.usecase.image.get.GetImageUseCase;
import com.desafios.galeriaimagensspring.application.usecase.image.save.SaveImageUseCase;
import com.desafios.galeriaimagensspring.application.usecase.image.save.SaveImageUseCaseImpl;
import com.desafios.galeriaimagensspring.application.usecase.image.update.UpdateImageUseCase;
import com.desafios.galeriaimagensspring.application.usecase.storage.upload.UploadImageUseCase;
import com.desafios.galeriaimagensspring.application.usecase.storage.upload.UploadImageUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageServiceConfig {

    @Bean
    public ImagemService imagemService(
            UploadImageUseCase uploadImageUseCase,
            SaveImageUseCase saveImageUseCase,
            GetImageUseCase getImageUseCase,
            DeleteImageUseCase deleteImageUseCase,
            UpdateImageUseCase updateImageUseCase,
            GetAuthenticateUserUseCase getAuthenticateUserUseCase,
            ValidateUserAuthorizationUseCase validateUserAuthorizationUseCase
    ) {
        return new ImagemService(
                uploadImageUseCase,
                saveImageUseCase,
                getImageUseCase,
                deleteImageUseCase,
                updateImageUseCase,
                getAuthenticateUserUseCase,
                validateUserAuthorizationUseCase
        );
    }
}
