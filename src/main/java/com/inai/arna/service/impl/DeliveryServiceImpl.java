package com.inai.arna.service.impl;

import com.inai.arna.dto.response.DeliveryView;
import com.inai.arna.repository.DeliveryCompanyRepository;
import com.inai.arna.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryCompanyRepository deliveryRepository;

    @Override
    public List<DeliveryView> getAll() {
        return deliveryRepository.findAllBy();
    }
}
