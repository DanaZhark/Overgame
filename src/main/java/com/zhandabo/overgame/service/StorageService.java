package com.zhandabo.overgame.service;

import com.zhandabo.overgame.model.dto.FileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    FileResponse uploadFile(MultipartFile file, String directory);

    void deleteFile(String path);
}
