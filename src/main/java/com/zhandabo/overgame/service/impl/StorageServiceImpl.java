package com.zhandabo.overgame.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.zhandabo.overgame.model.dto.FileResponse;
import com.zhandabo.overgame.service.StorageService;
import com.zhandabo.overgame.util.OvergameExceptionUtils;
import com.zhandabo.overgame.util.S3ObjectRequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private final AmazonS3 s3Client;
    @Value("${amazon.s3.bucket.name}")
    private String bucketName;
    @Value("${amazon.s3.bucket.url}")
    private String bucketFullUrl;

    public StorageServiceImpl(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @PostConstruct
    public void init() {
        if (s3Client.doesBucketExist(bucketName)) {
            log.info("Bucket with name \"{}\" is already exists!.", bucketName);
        } else {
            log.info("Bucket with name \"{}\" was successfully created!.", bucketName);
            s3Client.createBucket(bucketName);
        }
    }


    @Override
    public FileResponse uploadFile(MultipartFile file, String directory) {
        String filename = file.getOriginalFilename();
        String path = String.join("/", directory, filename);

        try {

            if (file.isEmpty()) {
                throw OvergameExceptionUtils.badRequest("Failed to store empty file");
            }

            PutObjectRequest putObjectRequest = S3ObjectRequestUtils.put(bucketName, file, path);
            PutObjectResult result = s3Client.putObject(putObjectRequest);
            log.info("PutObjectResult: ", result);
        } catch (Exception e) {
            throw OvergameExceptionUtils.internalServerError("Failed to store file " + filename, e);
        }
        return FileResponse
                .builder()
                .fullPath(String.join("", bucketFullUrl, path))
                .build();
    }

    @Override
    public void deleteFile(String path) {
        if (!path.startsWith(bucketFullUrl)) {
            throw OvergameExceptionUtils.badRequest("Not S3 object in bucket!");
        }
        String key = path.replace(bucketFullUrl, "");

        s3Client.deleteObject(S3ObjectRequestUtils.delete(bucketName, key));
    }
}