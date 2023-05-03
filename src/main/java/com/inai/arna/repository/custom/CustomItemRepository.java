package com.inai.arna.repository.custom;

import com.inai.arna.dto.request.Filter;
import com.inai.arna.dto.response.ItemResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomItemRepository {
    List<ItemResponse> findAll(Integer categoryId, Integer userId, Filter filter, String search, Pageable pageable);

}
