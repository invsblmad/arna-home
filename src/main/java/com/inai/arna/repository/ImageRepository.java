package com.inai.arna.repository;

import com.inai.arna.model.Image;
import com.inai.arna.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByItem(Item item);
}
