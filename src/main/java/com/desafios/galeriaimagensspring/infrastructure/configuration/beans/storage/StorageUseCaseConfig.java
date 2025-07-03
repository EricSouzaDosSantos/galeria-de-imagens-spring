package com.desafios.galeriaimagensspring.infrastructure.configuration.beans.storage;

import com.desafios.galeriaimagensspring.application.usecase.storage.upload.UploadImageUseCase;
import com.desafios.galeriaimagensspring.application.usecase.storage.upload.UploadImageUseCaseImpl;
import com.desafios.galeriaimagensspring.core.gateways.StorageGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageUseCaseConfig {

    @Bean
    public UploadImageUseCase uploadImageUseCase(StorageGateway storageGateway){
        return new UploadImageUseCaseImpl(storageGateway);
    }
}
