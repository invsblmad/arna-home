package com.inai.arna.dto.response;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface DeliveryView {
    @Value("#{target.id}")
    int getCompanyId();
    @Value("#{target.name}")
    String getCompanyName();
    List<DeliveryTypeView> getDeliveries();
}
