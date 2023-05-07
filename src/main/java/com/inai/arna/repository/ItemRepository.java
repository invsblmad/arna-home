package com.inai.arna.repository;

import com.inai.arna.model.Item;
import com.inai.arna.repository.custom.CustomItemRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>, CustomItemRepository {
}