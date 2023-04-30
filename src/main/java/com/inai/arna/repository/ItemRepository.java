package com.inai.arna.repository;

import com.inai.arna.dto.response.ItemView;
import com.inai.arna.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query(value = "select i.id, i.name, i.price, i.rating, fi.item_id is not null as isLiked, im.path as image " +
            "from items i left join favorite_items fi on (:userId is not null or fi.user_id = :userId) " +
            "and fi.item_id = i.id left join images im on (im.item_id = i.id and im.is_default is true) " +
            "where i.category_id = :categoryId", nativeQuery = true)
    Page<ItemView> findAllByCategory(Pageable pageable, @Param("categoryId") Integer categoryId,
                                     @Param("userId") Integer userId);
}