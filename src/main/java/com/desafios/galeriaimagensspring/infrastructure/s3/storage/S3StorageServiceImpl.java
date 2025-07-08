package com.desafios.galeriaimagensspring.infrastructure.s3.storage;


import com.desafios.galeriaimagensspring.core.gateways.StorageGateway;
import com.desafios.galeriaimagensspring.core.model.FileData;
import com.desafios.galeriaimagensspring.infrastructure.s3.exceptions.image.S3DeletingException;
import com.desafios.galeriaimagensspring.infrastructure.s3.exceptions.image.S3UploadException;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.util.UUID;

public class S3StorageServiceImpl implements StorageGateway {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3StorageServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String upload(FileData file, String folderName) {
        String fileName = folderName + "/"+ UUID.randomUUID() + "_" + file.fileName();

        try {

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .contentType(file.contentType())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.content()));
            return String.format("https://%s.s3.amazonaws.com/user/%s/%s", bucketName, folderName,  fileName);
        } catch (Exception e) {
            throw new S3UploadException("Error uploading amazon s3: " + e);
        }
    }

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

    public String updateImage(String oldImageURL, FileData file) {
        String newFileName = UUID.randomUUID() + "_" + file.fileName();

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(newFileName)
                    .contentType(file.contentType())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.content()));
            delete(oldImageURL);
            return String.format("https://%s.s3.amazonaws.com/%s", bucketName, newFileName);
        } catch (Exception e) {
            throw new S3UploadException("Error uploading amazon s3: "+e);
        }
    }

}
