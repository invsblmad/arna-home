package com.inai.arna.service;

import com.inai.arna.dto.request.ReviewRequest;
import com.inai.arna.dto.response.ReviewResponse;
import com.inai.arna.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    int getNumberOfReviews(Item Item);
    Page<ReviewResponse> getAll(Item item, Pageable pageable);
    ReviewResponse save(Item item, ReviewRequest reviewRequest);
}
