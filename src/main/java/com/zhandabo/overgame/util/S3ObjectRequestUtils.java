package com.zhandabo.overgame.util;

import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

@UtilityClass
public class S3ObjectRequestUtils {

    public PutObjectRequest put(String bucketName, MultipartFile file, String path) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType("image/jpeg");
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, path, file.getInputStream(), metadata);
            putObjectRequest.putCustomRequestHeader("x-amz-acl", "public-read");
            return putObjectRequest;
        } catch (Exception e) {
            throw OvergameExceptionUtils.internalServerError("Cannot read file");
        }
    }

    public DeleteObjectRequest delete(String bucketName, String key) {
        return new DeleteObjectRequest(bucketName, key);
    }
}