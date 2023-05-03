package com.inai.arna.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ItemResponse {
    private int id;
    private String name;
    private BigDecimal price;
    private BigDecimal rating;
    private boolean isLiked;
    private String imageUrl;
}
