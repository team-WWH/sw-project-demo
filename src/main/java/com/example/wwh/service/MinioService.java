package com.example.wwh.service;

import com.example.wwh.Config.MinioPropertie;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MinioService {
    @Autowired
    private  MinioClient minioClient;
    @Autowired
    private  MinioPropertie minioProperties;

    public void uploadFile(MultipartFile file, String objectName) throws Exception {
        ensureBucketExists();

        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioProperties.getBucket())
                            .object(objectName)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
            //log.info("Uploaded file: {} to bucket: {}", objectName, minioProperties.getBucket());
        }
    }
    public String getPresignedUrl( String objectName, int expiryTimeInSeconds) {
        try {
            return minioClient.getPresignedObjectUrl(
                    io.minio.GetPresignedObjectUrlArgs.builder()
                            .bucket(minioProperties.getBucket())
                            .object(objectName)
                            .method(Method.GET)
                            .expiry(expiryTimeInSeconds)
                            .build());
        } catch (Exception e) {
            throw new RuntimeException("Error generating presigned URL", e);
        }
    }

    public byte[] downloadFile(String objectName) throws Exception {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(minioProperties.getBucket())
                        .object(objectName)
                        .build())) {
            return stream.readAllBytes();
        }
    }

    public void uploadChunk(byte[] data, String chunkName, String contentType) throws Exception {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(minioProperties.getBucket())
                        .object(chunkName)
                        .stream(new ByteArrayInputStream(data), data.length, -1)
                        .contentType(contentType)
                        .build());
        //log.info("Uploaded chunk: {} to bucket: {}", chunkName, minioProperties.getBucket());
    }

    private void ensureBucketExists() throws Exception {
        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(minioProperties.getBucket())
                .build());

        if (!bucketExists) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .build());
           // log.info("Created bucket: {}", minioProperties.getBucket());
        }
    }
}
