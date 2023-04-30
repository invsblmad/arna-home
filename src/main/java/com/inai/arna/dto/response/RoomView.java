package com.inai.arna.dto.response;

import java.util.List;

public interface RoomView {
    int getId();
    String getName();
    List<ItemCategoryView> getItemCategories();
}
