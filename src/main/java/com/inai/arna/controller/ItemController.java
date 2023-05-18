package com.inai.arna.controller;

import com.inai.arna.dto.request.FilterRequest;
import com.inai.arna.dto.request.ImageRequest;
import com.inai.arna.dto.response.GroupedImageResponse;
import com.inai.arna.dto.response.ImageDetailsResponse;
import com.inai.arna.dto.response.ItemDetailsResponse;
import com.inai.arna.dto.response.ItemResponse;
import com.inai.arna.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public Page<ItemResponse> getAll(@RequestParam(value = "room", required = false) Integer roomId,
                                     @RequestParam(value = "category", required = false) Integer categoryId,
                                     @RequestParam(required = false) FilterRequest filter,
                                     @RequestParam(required = false) String search,
                                     Pageable pageable
    ) {
        return itemService.getAll(roomId, categoryId, filter, search, pageable);
    }

    @GetMapping("/{item-id}")
    public ItemDetailsResponse getById(@PathVariable("item-id") Integer itemId) {
        return itemService.getById(itemId);
    }

    @GetMapping("/{item-id}/images")
    public List<GroupedImageResponse> getImagesById(@PathVariable("item-id") Integer itemId) {
        return itemService.getImagesById(itemId);
    }

    @PostMapping("/{item-id}/images")
    public ImageDetailsResponse saveImage(@PathVariable("item-id") Integer itemId,
                                          @RequestPart("image") MultipartFile file,
                                          @RequestPart("json") ImageRequest imageRequest
    ) {
        return itemService.saveImage(itemId, file, imageRequest);
    }

}
