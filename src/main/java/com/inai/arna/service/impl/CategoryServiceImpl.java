package com.inai.arna.service.impl;

import com.inai.arna.dto.response.RoomView;
import com.inai.arna.exception.NotFoundException;
import com.inai.arna.repository.CategoryRepository;
import com.inai.arna.repository.RoomRepository;
import com.inai.arna.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final RoomRepository roomRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<RoomView> getAll() {
        return roomRepository.findAllBy();
    }

    @Override
    public void findRoomById(Integer roomId) {
        roomRepository.findById(roomId).orElseThrow(
                () -> new NotFoundException("Room with id " + roomId + " is not found")
        );
    }

    @Override
    public void findCategoryById(Integer categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(
                () -> new NotFoundException("Category with id " + categoryId + " is not found")
        );
    }

}
