package com.inai.arna.repository.custom;

import com.inai.arna.dto.request.FilterRequest;
import com.inai.arna.dto.response.ItemDetailsResponse;
import com.inai.arna.dto.response.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomItemRepository {
    Page<ItemResponse> findAll(Integer roomId, Integer categoryId, Integer userId, FilterRequest filter, String search, Pageable pageable);
    ItemDetailsResponse findById(Integer itemId, Integer userId);
    Page<ItemResponse> findUserFavorites(Integer userId, String search, Pageable pageable);

}
