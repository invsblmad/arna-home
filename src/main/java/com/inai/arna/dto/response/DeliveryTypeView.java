package com.inai.arna.dto.response;

import com.inai.arna.model.delivery.DeliveryType;

import java.math.BigDecimal;

public interface DeliveryTypeView {
    int getId();
    DeliveryType getType();
    String getTime();
    BigDecimal getPrice();
}
