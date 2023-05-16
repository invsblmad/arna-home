package com.inai.arna.service.impl;

import com.inai.arna.dto.request.ImageRequest;
import com.inai.arna.dto.response.ImageResponse;
import com.inai.arna.mapper.ImageMapper;
import com.inai.arna.model.Image;
import com.inai.arna.model.Item;
import com.inai.arna.repository.ImageRepository;
import com.inai.arna.service.CloudStorageService;
import com.inai.arna.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final CloudStorageService cloudStorageService;
    private final ImageMapper imageMapper;

    @Override
    public ImageResponse save(Item item, MultipartFile file, ImageRequest imageRequest) {
        Image image = imageMapper.toEntity(imageRequest);
        Map uploadResult = cloudStorageService.upload(file);

        setImageInfo(image, uploadResult, item);
        imageRepository.save(image);

        return imageMapper.toDto(image);
    }

    private void setImageInfo(Image image, Map uploadResult, Item item) {
        image.setUrl(uploadResult.get("url").toString());
        image.setId(uploadResult.get("public_id").toString());
        image.setItem(item);
    }
}
