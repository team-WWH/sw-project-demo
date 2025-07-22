package com.example.wwh.Config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinioConfig {
    @Autowired
    private  MinioPropertie minioProperties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(
                        minioProperties.getAccessKey(),
                        minioProperties.getSecretKey()
                )
               // .secure(minioProperties.isSecure())  // 使用 isSecure() 方法
                .build();
    }
}