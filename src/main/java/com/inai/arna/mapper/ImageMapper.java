package com.inai.arna.mapper;

import com.inai.arna.dto.request.ImageRequest;
import com.inai.arna.dto.response.ImageDetailsResponse;
import com.inai.arna.dto.response.ImageResponse;
import com.inai.arna.model.Image;

import java.util.List;

public interface ImageMapper {
    Image toEntity(ImageRequest imageRequest);
    ImageDetailsResponse toDetailsDto(Image image);
    List<ImageResponse> toDtoList(List<Image> image);
}
