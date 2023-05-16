package com.inai.arna.service.impl;

import com.inai.arna.dto.request.FilterRequest;
import com.inai.arna.dto.request.ImageRequest;
import com.inai.arna.dto.response.ImageResponse;
import com.inai.arna.dto.response.ItemDetailsResponse;
import com.inai.arna.dto.response.ItemResponse;
import com.inai.arna.exception.NotFoundException;
import com.inai.arna.model.Item;
import com.inai.arna.repository.CategoryRepository;
import com.inai.arna.repository.ItemRepository;
import com.inai.arna.repository.RoomRepository;
import com.inai.arna.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final CategoryRepository categoryRepository;
    private final RoomRepository roomRepository;
    private final ItemRepository itemRepository;
    private final UserService userService;
    private final ReviewService reviewService;
    private final ImageService imageService;

    @Override
    public Page<ItemResponse> getAll(Integer roomId, Integer categoryId, FilterRequest filter,
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

    @Override
    public ImageResponse saveImage(Integer itemId, MultipartFile file, ImageRequest imageRequest) {
        Item item = findItemById(itemId);
        return imageService.save(item, file, imageRequest);
    }

    private void findRoomById(Integer id) {
        roomRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Room with id " + id + " is not found")
        );
    }

    private void findCategoryById(Integer id) {
        categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Category with id " + id + " is not found")
        );
    }

    private Item findItemById(Integer id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Item with id " + id + " is not found")
        );
    }




}
