package com.inai.arna.service.impl;

import com.inai.arna.dto.request.ReviewRequest;
import com.inai.arna.dto.response.ReviewResponse;
import com.inai.arna.mapper.ReviewMapper;
import com.inai.arna.model.Item;
import com.inai.arna.model.Review;
import com.inai.arna.repository.ReviewRepository;
import com.inai.arna.service.AuthService;
import com.inai.arna.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final AuthService authService;
    @Override
    public int getNumberOfReviews(Item item) {
        return reviewRepository.countAllByItem(item);
    }

    @Override
    public Page<ReviewResponse> getAll(Item item, Pageable pageable) {
        return reviewRepository.findAllByItem(item, pageable);
    }

    @Override
    public ReviewResponse save(Item item, ReviewRequest reviewRequest) {
        Review review = reviewMapper.toEntity(reviewRequest);

        review.setItem(item);
        review.setUser(authService.getAuthenticatedUser());

        reviewRepository.save(review);
        return reviewMapper.toDto(review);
    }
}
