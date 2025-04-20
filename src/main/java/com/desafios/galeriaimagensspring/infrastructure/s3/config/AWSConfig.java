package com.desafios.galeriaimagensspring.infrastructure.s3.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Value("${aws.region}")
    private String region;

    @Bean
    public AmazonS3 amazonS3(){
        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(region)
                .build();

        return s3Client;
    }
}
