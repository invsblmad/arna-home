package com.inai.arna.dto.response;

import java.math.BigDecimal;

public interface ItemView {
    int getId();
    String getName();
    BigDecimal getPrice();
    BigDecimal getRating();
    boolean getIsLiked();
    String getImage();
}
