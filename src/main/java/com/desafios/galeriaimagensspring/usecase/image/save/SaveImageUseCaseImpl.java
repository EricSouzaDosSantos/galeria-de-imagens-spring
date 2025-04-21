package com.desafios.galeriaimagensspring.usecase.image.save;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.desafios.galeriaimagensspring.domain.exception.image.S3UploadException;
import com.desafios.galeriaimagensspring.domain.repository.ImagensRepository;
import com.desafios.galeriaimagensspring.domain.repository.StorageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SaveImageUseCaseImpl implements SaveImageUseCase {

    private final StorageService storageService;

    @Override
    public String execute(MultipartFile image) {
        return storageService.upload(image);
    }
}
