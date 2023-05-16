package com.inai.arna.mapper.impl;

import com.inai.arna.dto.request.ImageRequest;
import com.inai.arna.dto.response.ImageResponse;
import com.inai.arna.mapper.ImageMapper;
import com.inai.arna.model.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageMapperImpl implements ImageMapper {

    @Override
    public Image toEntity(ImageRequest imageRequest) {
        Image image = new Image();
        image.setColorHex(imageRequest.getColorHex());
        image.setDefault(imageRequest.isDefault());
        return image;
    }

    @Override
    public ImageResponse toDto(Image image) {
        ImageResponse imageResponse = new ImageResponse();
        imageResponse.setId(image.getId());
        imageResponse.setUrl(image.getUrl());
        imageResponse.setColorHex(image.getColorHex());
        imageResponse.setDefault(image.isDefault());
        return imageResponse;
    }
}
