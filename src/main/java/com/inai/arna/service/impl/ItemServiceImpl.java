package com.inai.arna.service.impl;

import com.inai.arna.dto.request.Filter;
import com.inai.arna.dto.response.ItemDetailsResponse;
import com.inai.arna.dto.response.ItemResponse;
import com.inai.arna.exception.NotFoundException;
import com.inai.arna.model.Item;
import com.inai.arna.model.category.Category;
import com.inai.arna.model.category.Room;
import com.inai.arna.repository.CategoryRepository;
import com.inai.arna.repository.ItemRepository;
import com.inai.arna.repository.RoomRepository;
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
    private final CategoryRepository categoryRepository;
    private final RoomRepository roomRepository;
    private final ItemRepository itemRepository;
    private final UserService userService;
    private final ReviewService reviewService;

    @Override
    public Page<ItemResponse> getAll(Integer roomId, Integer categoryId, Filter filter,
                                     String search, Pageable pageable) {

        if (roomId != null) findRoomById(roomId);
        if (categoryId != null) findCategoryById(categoryId);

        Integer userId = userService.getAuthenticatedUserId();
        return itemRepository.findAll(roomId, categoryId, userId, filter, search, pageable);
    }

    @Override
    public ItemDetailsResponse getById(Integer itemId) {
        Item item = findItemById(itemId);
        Integer userId = userService.getAuthenticatedUserId();

        ItemDetailsResponse details = itemRepository.findById(itemId, userId);
        details.setNumberOfReviews(reviewService.getNumberOfReviews(item));

        return details;
    }

    private Room findRoomById(Integer id) {
        return roomRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Room with id " + id + " is not found")
        );
    }

    private Category findCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Category with id " + id + " is not found")
        );
    }

    private Item findItemById(Integer id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Item with id " + id + " is not found")
        );
    }




}
