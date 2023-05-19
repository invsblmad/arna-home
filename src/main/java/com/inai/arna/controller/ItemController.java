package com.inai.arna.controller;

import com.inai.arna.dto.request.FilterRequest;
import com.inai.arna.dto.request.ImageRequest;
import com.inai.arna.dto.request.ReviewRequest;
import com.inai.arna.dto.response.*;
import com.inai.arna.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/public/items")
    public Page<ItemResponse> getAll(@RequestParam(value = "room", required = false) Integer roomId,
                                     @RequestParam(value = "category", required = false) Integer categoryId,
                                     @RequestParam(required = false) FilterRequest filter,
                                     @RequestParam(required = false) String search,
                                     Pageable pageable
    ) {
        return itemService.getAll(roomId, categoryId, filter, search, pageable);
    }

    @GetMapping("/public/items/{item-id}")
    public ItemDetailsResponse getById(@PathVariable("item-id") Integer itemId) {
        return itemService.getById(itemId);
    }

    @GetMapping("/public/items/{item-id}/images")
    public List<GroupedImageResponse> getImagesById(@PathVariable("item-id") Integer itemId) {
        return itemService.getImagesById(itemId);
    }

    @PostMapping("/protected/items/{item-id}/images")
    public ImageDetailsResponse saveImage(@PathVariable("item-id") Integer itemId,
                                          @RequestPart("image") MultipartFile file,
                                          @RequestPart("json") ImageRequest imageRequest
    ) {
        return itemService.saveImage(itemId, file, imageRequest);
    }

    @GetMapping("/public/items/{item-id}/reviews")
    public Page<ReviewResponse> getReviewsById(@PathVariable("item-id") Integer itemId,
                                           Pageable pageable
    ) {
        return itemService.getReviewsById(itemId, pageable);
    }

    @PostMapping("/protected/items/{item-id}/reviews")
    public ReviewResponse saveReview(@PathVariable("item-id") Integer itemId,
                                 @RequestBody ReviewRequest reviewRequest
    ) {
        return itemService.saveReview(itemId, reviewRequest);
    }

}
