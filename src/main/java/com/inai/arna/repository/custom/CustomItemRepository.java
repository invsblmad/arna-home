package com.inai.arna.repository.custom;

import com.inai.arna.dto.request.Filter;
import com.inai.arna.dto.response.ItemDetailsResponse;
import com.inai.arna.dto.response.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomItemRepository {
    Page<ItemResponse> findAll(Integer categoryId, Integer userId, Filter filter, String search, Pageable pageable);
    ItemDetailsResponse findById(Integer itemId, Integer userId);

}
