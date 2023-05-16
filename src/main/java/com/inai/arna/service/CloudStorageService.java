package com.inai.arna.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CloudStorageService {
    Map upload(MultipartFile file);
    void delete(String publicId);

}
