package com.inai.arna.service.impl;

import com.inai.arna.model.Item;
import com.inai.arna.repository.ReviewRepository;
import com.inai.arna.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    @Override
    public int getNumberOfReviews(Item item) {
        return reviewRepository.countAllByItem(item);
    }
}
