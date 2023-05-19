package com.inai.arna.service;

import com.inai.arna.dto.response.RoomView;

import java.util.List;

public interface CategoryService {
    List<RoomView> getAll();
    void findRoomById(Integer roomId);
    void findCategoryById(Integer categoryId);
}
