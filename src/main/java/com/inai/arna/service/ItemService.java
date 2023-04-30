package com.inai.arna.service;

import com.inai.arna.dto.response.ItemView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {
    Page<ItemView> getAll(Pageable pageable, int roomId, int categoryId);

}
