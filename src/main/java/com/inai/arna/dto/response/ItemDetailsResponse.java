package com.inai.arna.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemDetailsResponse {
    private int id;
    private String name;
    private BigDecimal price;
    private BigDecimal rating;
    private boolean isLiked;
    private String description;
    private int numberOfReviews;

    public ItemDetailsResponse(int id, String name, BigDecimal price, BigDecimal rating, boolean isLiked, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.isLiked = isLiked;
        this.description = description;
    }
}
