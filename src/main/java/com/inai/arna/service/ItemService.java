package com.inai.arna.service;

import com.inai.arna.dto.request.FilterRequest;
import com.inai.arna.dto.request.ImageRequest;
import com.inai.arna.dto.response.ImageResponse;
import com.inai.arna.dto.response.ItemDetailsResponse;
import com.inai.arna.dto.response.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService {
    Page<ItemResponse> getAll(Integer roomId, Integer categoryId, FilterRequest filter,
                              String search, Pageable pageable);
    ItemDetailsResponse getById(Integer itemId);
    ImageResponse saveImage(Integer itemId, MultipartFile file, ImageRequest imageRequest);

}
