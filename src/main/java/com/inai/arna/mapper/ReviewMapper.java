package com.inai.arna.mapper;

import com.inai.arna.dto.request.ReviewRequest;
import com.inai.arna.dto.response.ReviewResponse;
import com.inai.arna.model.Review;

public interface ReviewMapper {
    Review toEntity(ReviewRequest reviewRequest);
    ReviewResponse toDto(Review review);
}
