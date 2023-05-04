package com.inai.arna.repository;

import com.inai.arna.dto.response.ItemDetailsView;
import com.inai.arna.model.Item;
import com.inai.arna.repository.custom.CustomItemRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>, CustomItemRepository {

    @Query(value = "select i.id, i.name, i.price, i.rating, fi.item_id is not null as isLiked, i.description " +
            "from items i left join favorite_items fi on (:userId is not null and fi.user_id = :userId) " +
            "and fi.item_id = i.id where i.id = :itemId", nativeQuery = true)
    ItemDetailsView findById(@Param("itemId") Integer itemId, @Param("userId") Integer userId);
}