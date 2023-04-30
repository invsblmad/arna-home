package com.inai.arna.service.impl;

import com.inai.arna.dto.response.ItemView;
import com.inai.arna.model.category.ItemCategory;
import com.inai.arna.repository.ItemRepository;
import com.inai.arna.service.CategoryService;
import com.inai.arna.service.ItemService;
import com.inai.arna.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    @Override
    public Page<ItemView> getAll(Pageable pageable, int roomId, int categoryId) {
        ItemCategory category = categoryService.findItemCategory(roomId, categoryId);
        Integer userId = userService.getAuthenticatedUserId();
        return itemRepository.findAllByCategory(pageable, category.getId(), userId);
    }
}
