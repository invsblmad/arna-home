package com.inai.arna.service.impl;

import com.inai.arna.dto.response.CategoryView;
import com.inai.arna.repository.CategoryRepository;
import com.inai.arna.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryView> getAll() {
        return categoryRepository.findAllBy();
    }
}
