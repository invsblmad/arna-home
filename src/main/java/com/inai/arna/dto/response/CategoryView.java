package com.inai.arna.dto.response;

import java.util.List;

public interface CategoryView {
    int getId();
    String getName();
    List<SubCategoryView> getSubCategories();
}
