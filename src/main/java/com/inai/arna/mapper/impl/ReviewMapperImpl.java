package com.inai.arna.mapper.impl;

import com.inai.arna.dto.request.ReviewRequest;
import com.inai.arna.dto.response.ReviewResponse;
import com.inai.arna.mapper.ReviewMapper;
import com.inai.arna.model.Review;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewMapperImpl implements ReviewMapper {
    @Override
    public Review toEntity(ReviewRequest reviewRequest) {
        Review review = new Review();
        review.setMark(reviewRequest.getMark());
        review.setText(reviewRequest.getText());
        review.setCreatedAt(LocalDateTime.now());
        return review;
    }

    @Override
    public ReviewResponse toDto(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getUser().getFirstName(),
                review.getUser().getLastName(),
                review.getMark(),
                review.getCreatedAt(),
                review.getText()
        );
    }
}
