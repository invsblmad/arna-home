package com.inai.arna.service;

import com.inai.arna.dto.request.FilterRequest;
import com.inai.arna.dto.request.ImageRequest;
import com.inai.arna.dto.request.ReviewRequest;
import com.inai.arna.dto.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
    Page<ItemResponse> getAll(Integer roomId, Integer categoryId, FilterRequest filter,
                              String search, Pageable pageable);
    ItemDetailsResponse getById(Integer itemId);
    ImageDetailsResponse saveImage(Integer itemId, MultipartFile file, ImageRequest imageRequest);
    List<GroupedImageResponse> getImagesById(Integer itemId);
    Page<ReviewResponse> getReviewsById(Integer itemId, Pageable pageable);
    ReviewResponse saveReview(Integer itemId, ReviewRequest reviewRequest);

}
