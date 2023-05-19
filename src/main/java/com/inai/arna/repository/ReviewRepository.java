package com.inai.arna.repository;

import com.inai.arna.dto.response.ReviewResponse;
import com.inai.arna.model.Item;
import com.inai.arna.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("select count(r.id) as number from Review r where r.item = :item")
    int countAllByItem(@Param("item") Item item);
    @Query("select new com.inai.arna.dto.response.ReviewResponse(r.id, r.user.firstName, r.user.lastName, " +
            "r.mark, r.createdAt, r.text) from Review r where r.item = :item")
    Page<ReviewResponse> findAllByItem(@Param("item") Item item, Pageable pageable);
}
