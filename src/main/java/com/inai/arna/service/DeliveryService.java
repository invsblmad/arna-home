package com.inai.arna.service;

import com.inai.arna.dto.response.DeliveryView;

import java.util.List;

public interface DeliveryService {
    List<DeliveryView> getAll();
}
