package com.inai.arna.service.impl;

import com.inai.arna.dto.response.ItemDetailsView;
import com.inai.arna.dto.response.ItemView;
import com.inai.arna.model.Item;
import com.inai.arna.model.category.ItemCategory;
import com.inai.arna.repository.ItemRepository;
import com.inai.arna.repository.ReviewRepository;
import com.inai.arna.service.CategoryService;
import com.inai.arna.service.ItemService;
import com.inai.arna.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ReviewRepository reviewRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    @Override
    public Page<ItemView> getAll(Integer roomId, Integer categoryId, Pageable pageable) {
        ItemCategory category = categoryService.findItemCategory(roomId, categoryId);
        Integer userId = userService.getAuthenticatedUserId();
        return itemRepository.findAll(category.getId(), userId, pageable);
    }

    @Override
    public ItemDetailsView getById(Integer id) {
        return itemRepository.findById(id, userService.getAuthenticatedUserId());
    }

    public Item findItemById(Integer id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Item with id " + id + " is not found")
        );
    }


}
