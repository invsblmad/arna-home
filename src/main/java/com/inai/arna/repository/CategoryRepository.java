package com.inai.arna.repository;

import com.inai.arna.dto.response.CategoryView;
import com.inai.arna.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<CategoryView> findAllBy();
}
