package com.inai.arna.service;

import com.inai.arna.dto.request.ImageRequest;
import com.inai.arna.dto.response.ImageResponse;
import com.inai.arna.model.Item;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ImageResponse save(Item item, MultipartFile file, ImageRequest imageRequest);
}
