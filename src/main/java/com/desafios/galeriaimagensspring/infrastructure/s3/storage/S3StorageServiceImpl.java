package com.desafios.galeriaimagensspring.infrastructure.s3.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.desafios.galeriaimagensspring.domain.exception.image.S3DeletingException;
import com.desafios.galeriaimagensspring.domain.exception.image.S3UploadException;
import com.desafios.galeriaimagensspring.domain.repository.StorageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3StorageServiceImpl implements StorageService {

    private final AmazonS3 s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Override
    public String upload(MultipartFile file) {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            s3Client.putObject(bucketName, fileName, file.getInputStream(), metadata);
            return fileName;

        } catch (Exception e) {
            throw new S3UploadException("Error uploading amazon s3: "+e);
        }
    }

    @Override
    public void delete(String url) {
        try {
            s3Client.deleteObject(bucketName, url);
        } catch (Exception e) {
            throw new S3DeletingException("Error deleting image for s3: "+ e);
        }
    }

    @Override
    public void createUserFolder(String folderName) {
        String folderKey = folderName.endsWith("/") ? folderName : folderName + "/";
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);

        s3Client.putObject(bucketName, folderKey, new ByteArrayInputStream(new byte[0]), metadata);
    }

}
