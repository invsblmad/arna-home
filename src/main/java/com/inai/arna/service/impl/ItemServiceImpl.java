package com.inai.arna.service.impl;

import com.inai.arna.dto.response.ItemDetailsResponse;
import com.inai.arna.dto.response.ItemDetailsView;
import com.inai.arna.dto.response.ItemView;
import com.inai.arna.exception.NotFoundException;
import com.inai.arna.model.Item;
import com.inai.arna.model.category.ItemCategory;
import com.inai.arna.repository.ItemRepository;
import com.inai.arna.service.CategoryService;
import com.inai.arna.service.ItemService;
import com.inai.arna.service.ReviewService;
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
    private final ReviewService reviewService;

    @Override
    public Page<ItemView> getAll(Integer roomId, Integer categoryId, Pageable pageable) {
        ItemCategory category = categoryService.findItemCategory(roomId, categoryId);
        Integer userId = userService.getAuthenticatedUserId();
        return itemRepository.findAll(category.getId(), userId, pageable);
    }

    @Override
    public ItemDetailsResponse getById(Integer itemId) {
        Item item = findItemById(itemId);
        Integer userId = userService.getAuthenticatedUserId();

        ItemDetailsView details = itemRepository.findById(itemId, userId);
        int numberOfReviews = reviewService.getNumberOfReviews(item);

        return new ItemDetailsResponse(details, numberOfReviews);
    }

    private Item findItemById(Integer id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Item with id " + id + " is not found")
        );
    }


}
