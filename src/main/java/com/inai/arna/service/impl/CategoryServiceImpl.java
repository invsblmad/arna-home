package com.inai.arna.service.impl;

import com.inai.arna.dto.response.RoomView;
import com.inai.arna.exception.EntityNotFoundException;
import com.inai.arna.model.category.ItemCategory;
import com.inai.arna.repository.ItemCategoryRepository;
import com.inai.arna.repository.RoomRepository;
import com.inai.arna.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final RoomRepository roomRepository;
    private final ItemCategoryRepository itemCategoryRepository;

    @Override
    public List<RoomView> getAll() {
        return roomRepository.findAllBy();
    }

    @Override
    public ItemCategory findItemCategory(Integer roomId, Integer categoryId) {
        return itemCategoryRepository.findByRoom_IdAndCategory_Id(roomId, categoryId).orElseThrow(
                () -> new EntityNotFoundException("Item category is not found: wrong room id or category id")
        );
    }
}
