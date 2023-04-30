package com.inai.arna.repository;

import com.inai.arna.model.category.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Integer> {
    Optional<ItemCategory> findByRoom_IdAndCategory_Id(Integer roomId, Integer categoryId);
}
