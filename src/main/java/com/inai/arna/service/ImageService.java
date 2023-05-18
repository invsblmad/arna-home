package com.inai.arna.service;

import com.inai.arna.dto.request.ImageRequest;
import com.inai.arna.dto.response.GroupedImageResponse;
import com.inai.arna.dto.response.ImageDetailsResponse;
import com.inai.arna.model.Item;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    ImageDetailsResponse save(Item item, MultipartFile file, ImageRequest imageRequest);
    List<GroupedImageResponse> getAll(Item item);
}
