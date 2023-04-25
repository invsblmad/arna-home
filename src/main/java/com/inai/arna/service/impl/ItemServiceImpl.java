package com.inai.arna.service.impl;

import com.inai.arna.repository.ItemRepository;
import com.inai.arna.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
}
