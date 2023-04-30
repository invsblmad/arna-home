package com.inai.arna.controller;

import com.inai.arna.dto.response.ItemView;
import com.inai.arna.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/{room-id}/{category-id}")
    public Page<ItemView> getAll(Pageable pageable, @PathVariable("room-id") Integer roomId,
                                 @PathVariable("category-id") Integer categoryId
    ) {
        return itemService.getAll(pageable, roomId, categoryId);
    }
}
