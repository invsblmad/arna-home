package com.inai.arna.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.inai.arna.exception.CloudStorageError;
import com.inai.arna.service.CloudStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudStorageServiceImpl implements CloudStorageService {
    private final Cloudinary cloudinary;
    public Map upload(MultipartFile file) {
        Map uploadResult;
        try {
            uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new CloudStorageError(e.getMessage());
        }
        return uploadResult;
    }

    @Override
    public void delete(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new CloudStorageError(e.getMessage());
        }
    }
}
