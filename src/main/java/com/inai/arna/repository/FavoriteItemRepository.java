package com.inai.arna.repository;

import com.inai.arna.model.favorites.FavoriteItem;
import com.inai.arna.model.favorites.FavoriteItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteItemRepository extends JpaRepository<FavoriteItem, FavoriteItemId> {
    Optional<FavoriteItem> findByUserIdAndItemId(Integer userId, Integer itemId);
}
