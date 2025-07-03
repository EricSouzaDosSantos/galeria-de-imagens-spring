package com.desafios.galeriaimagensspring.infrastructure.configuration.beans.storage;

import com.desafios.galeriaimagensspring.core.gateways.StorageGateway;
import com.desafios.galeriaimagensspring.infrastructure.s3.storage.S3StorageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class StorageConfig {

    @Bean
    public StorageGateway storageGateway(S3Client s3Client) {
        return new S3StorageServiceImpl(
                s3Client
        );
    }
}
