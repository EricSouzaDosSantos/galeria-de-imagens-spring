package com.desafios.galeriaimagensspring.infrastructure.s3.storage;


import com.desafios.galeriaimagensspring.infrastructure.s3.exceptions.image.S3DeletingException;
import com.desafios.galeriaimagensspring.infrastructure.s3.exceptions.image.S3UploadException;
import com.desafios.galeriaimagensspring.core.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3StorageServiceImpl implements StorageService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Override
    public String upload(MultipartFile file, String folderName) {
        String fileName = folderName + "/"+ UUID.randomUUID() + "_" + file.getOriginalFilename();

        try {

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
            return String.format("https://%s.s3.amazonaws.com/user/%s/%s", bucketName, folderName,  fileName);
        } catch (Exception e) {
            throw new S3UploadException("Error uploading amazon s3: " + e);
        }
    }

    @Override
    public void delete(String url) {
        try {
            DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(url)
                    .build();

            s3Client.deleteObject(deleteRequest);
        } catch (Exception e) {
            throw new S3DeletingException("Error deleting image for s3: "+ e);
        }
    }

    @Override
    public void createUserFolder(String folderName) {
        String folderStart = folderName.startsWith("user/") ? folderName : "user/" + folderName;
        String folderKey = folderStart.endsWith("/") ? folderName : folderName + "/";

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(folderKey)
                    .contentLength(0L)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(new byte[0]));
        } catch (Exception e) {
            throw new S3UploadException("Error creating folder in S3: " + e);
        }
    }

    @Override
    public String updateImage(String oldImageURL, MultipartFile file) {
        String newFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(newFileName)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
            delete(oldImageURL);
            return String.format("https://%s.s3.amazonaws.com/%s", bucketName, newFileName);
        } catch (Exception e) {
            throw new S3UploadException("Error uploading amazon s3: "+e);
        }
    }

}
