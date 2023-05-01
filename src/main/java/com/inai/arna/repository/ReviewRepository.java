package com.inai.arna.repository;

import com.inai.arna.model.Item;
import com.inai.arna.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("select count(r.id) as number from Review r where r.item = :item")
    int countAllByItem(@Param("item") Item item);
}
