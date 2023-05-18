package com.inai.arna.mapper.impl;

import com.inai.arna.dto.request.ImageRequest;
import com.inai.arna.dto.response.ImageDetailsResponse;
import com.inai.arna.dto.response.ImageResponse;
import com.inai.arna.mapper.ImageMapper;
import com.inai.arna.model.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public ImageDetailsResponse toDetailsDto(Image image) {
        return new ImageDetailsResponse(
                image.getId(),
                image.getUrl(),
                image.getColorHex(),
                image.isDefault()
        );
    }

    @Override
    public List<ImageResponse> toDtoList(List<Image> image) {
        return image.stream().map(this::toDto).collect(Collectors.toList());
    }

    private ImageResponse toDto(Image image) {
        return new ImageResponse(
                image.getId(),
                image.getUrl(),
                image.isDefault()
        );
    }
}
