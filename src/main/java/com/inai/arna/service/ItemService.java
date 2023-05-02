package com.inai.arna.service;

import com.inai.arna.dto.request.FilterType;
import com.inai.arna.dto.response.ItemDetailsResponse;
import com.inai.arna.dto.response.ItemView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {
    Page<ItemView> getAll(Integer roomId, Integer categoryId, FilterType filterType,
                          String search, Pageable pageable);
    ItemDetailsResponse getById(Integer itemId);

}
