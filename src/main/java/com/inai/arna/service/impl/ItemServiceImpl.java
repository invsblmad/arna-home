package com.inai.arna.service.impl;

import com.inai.arna.dto.request.FilterRequest;
import com.inai.arna.dto.request.ImageRequest;
import com.inai.arna.dto.request.ReviewRequest;
import com.inai.arna.dto.response.*;
import com.inai.arna.exception.ItemAlreadyLikedException;
import com.inai.arna.exception.NotFoundException;
import com.inai.arna.model.Item;
import com.inai.arna.model.favorites.FavoriteItem;
import com.inai.arna.repository.FavoriteItemRepository;
import com.inai.arna.repository.ItemRepository;
import com.inai.arna.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final FavoriteItemRepository favoriteItemRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ReviewService reviewService;
    private final ImageService imageService;

    @Override
    public Page<ItemResponse> getAll(Integer roomId, Integer categoryId, FilterRequest filter,
                                     String search, Pageable pageable) {

        if (roomId != null) categoryService.findRoomById(roomId);
        if (categoryId != null) categoryService.findCategoryById(categoryId);

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
    public ImageDetailsResponse saveImage(Integer itemId, MultipartFile file, ImageRequest imageRequest) {
        Item item = findItemById(itemId);
        return imageService.save(item, file, imageRequest);
    }

    @Override
    public List<GroupedImageResponse> getImagesById(Integer itemId) {
        Item item = findItemById(itemId);
        return imageService.getAll(item);
    }

    @Override
    public Page<ReviewResponse> getReviewsById(Integer itemId, Pageable pageable) {
        Item item = findItemById(itemId);
        return reviewService.getAll(item, pageable);
    }

    @Override
    public ReviewResponse saveReview(Integer itemId, ReviewRequest reviewRequest) {
        Item item = findItemById(itemId);
        return reviewService.save(item, reviewRequest);
    }

    @Override
    public Page<ItemResponse> getUserFavorites(String search, Pageable pageable) {
        Integer userId = userService.getAuthenticatedUserId();
        return itemRepository.findUserFavorites(userId, search, pageable);
    }

    @Override
    public void saveToFavorites(Integer itemId) {
        Item item = findItemById(itemId);
        Integer userId = userService.getAuthenticatedUserId();

        checkIfItemAlreadyLiked(userId, itemId);
        favoriteItemRepository.save(new FavoriteItem(userId, itemId));
    }

    @Override
    public void deleteFromFavorites(Integer itemId) {
        Item item = findItemById(itemId);
        Integer userId = userService.getAuthenticatedUserId();

        var favoriteItem = favoriteItemRepository.findByUserIdAndItemId(userId, itemId);
        favoriteItem.ifPresent(favoriteItemRepository::delete);
    }

    private Item findItemById(Integer id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Item with id " + id + " is not found")
        );
    }

    private void checkIfItemAlreadyLiked(Integer userId, Integer itemId) {
        if (favoriteItemRepository.findByUserIdAndItemId(userId, itemId).isPresent())
            throw new ItemAlreadyLikedException("Item with id " + itemId + " has already been liked");
    }


}
