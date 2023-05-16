package com.inai.arna.mapper;

import com.inai.arna.dto.request.ImageRequest;
import com.inai.arna.dto.response.ImageResponse;
import com.inai.arna.model.Image;

public interface ImageMapper {
    Image toEntity(ImageRequest imageRequest);
    ImageResponse toDto(Image image);
}
