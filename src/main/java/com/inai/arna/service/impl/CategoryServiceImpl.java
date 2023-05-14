package com.inai.arna.service.impl;

import com.inai.arna.dto.response.RoomView;
import com.inai.arna.repository.RoomRepository;
import com.inai.arna.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final RoomRepository roomRepository;

    @Override
    public List<RoomView> getAll() {
        return roomRepository.findAllBy();
    }

}
