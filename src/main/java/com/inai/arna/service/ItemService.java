package com.inai.arna.service;

import com.inai.arna.dto.request.Filter;
import com.inai.arna.dto.response.ItemDetailsResponse;
import com.inai.arna.dto.response.ItemResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {
    List<ItemResponse> getAll(Integer roomId, Integer categoryId, Filter filter,
                              String search, Pageable pageable);
    ItemDetailsResponse getById(Integer itemId);

}
