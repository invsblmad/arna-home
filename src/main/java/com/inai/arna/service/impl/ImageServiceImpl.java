package com.inai.arna.service.impl;

import com.inai.arna.dto.request.ImageRequest;
import com.inai.arna.dto.response.GroupedImageResponse;
import com.inai.arna.dto.response.ImageDetailsResponse;
import com.inai.arna.mapper.ImageMapper;
import com.inai.arna.model.Image;
import com.inai.arna.model.Item;
import com.inai.arna.repository.ImageRepository;
import com.inai.arna.service.CloudStorageService;
import com.inai.arna.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final CloudStorageService cloudStorageService;
    private final ImageMapper imageMapper;

    @Override
    public ImageDetailsResponse save(Item item, MultipartFile file, ImageRequest imageRequest) {
        Image image = imageMapper.toEntity(imageRequest);
        Map uploadResult = cloudStorageService.upload(file);

        setImageInfo(image, uploadResult, item);
        imageRepository.save(image);

        return imageMapper.toDetailsDto(image);
    }

    @Override
    public List<GroupedImageResponse> getAll(Item item) {
        List<Image> images = imageRepository.findByItem(item);
        return groupImagesByColor(images);
    }

    private void setImageInfo(Image image, Map uploadResult, Item item) {
        image.setUrl(uploadResult.get("url").toString());
        image.setId(uploadResult.get("public_id").toString());
        image.setItem(item);
    }

    private List<GroupedImageResponse> groupImagesByColor(List<Image> images) {
        Map<String, List<Image>> groupedImages = images.stream()
                .collect(Collectors.groupingBy(Image::getColorHex));

        return groupedImages.entrySet().stream()
                .map(entry ->
                    new GroupedImageResponse(entry.getKey(), imageMapper.toDtoList(entry.getValue()))
                ).collect(Collectors.toList());
    }

}
