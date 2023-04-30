package com.inai.arna.service;

import com.inai.arna.dto.response.RoomView;
import com.inai.arna.model.category.ItemCategory;

import java.util.List;

public interface CategoryService {
    List<RoomView> getAll();
    ItemCategory findItemCategory(int roomId, int categoryId);
}
