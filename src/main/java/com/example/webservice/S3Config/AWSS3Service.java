package com.example.webservice.S3Config;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

public interface AWSS3Service {
    // @Async annotation ensures that the method is executed in a different background thread
    // but not consume the main thread.
    @Async
    void uploadFile(MultipartFile multipartFile, String userId);

    // @Async annotation ensures that the method is executed in a different background thread
    // but not consume the main thread.
    @Async
    byte[] downloadFile(String keyName);

    // @Async annotation ensures that the method is executed in a different background thread
    // but not consume the main thread.
    @Async
    void deleteFile(String keyName);
}
