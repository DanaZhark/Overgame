package com.zhandabo.overgame.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class ImgFileUtils {

    public static String saveFile(MultipartFile file, String uploadPath) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String fileName = file.getOriginalFilename();
        File newFile = new File(uploadPath + fileName);

        try {
            inputStream = file.getInputStream();

            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newFile.getAbsolutePath();
    }
}
