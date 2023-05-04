package com.inai.arna.repository.custom.impl;

import com.inai.arna.dto.request.Filter;
import com.inai.arna.dto.response.ItemResponse;
import com.inai.arna.jooq.model.Tables;
import com.inai.arna.jooq.model.tables.FavoriteItems;
import com.inai.arna.jooq.model.tables.Images;
import com.inai.arna.jooq.model.tables.Items;
import com.inai.arna.repository.custom.CustomItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomItemRepositoryImpl implements CustomItemRepository {
    private final DSLContext context;
    private Items items;
    private Images images;
    private FavoriteItems favoriteItems;

    @PostConstruct
    private void init() {
        items = Tables.ITEMS;
        images = Tables.IMAGES;
        favoriteItems = Tables.FAVORITE_ITEMS;
    }
    @Override
    public Page<ItemResponse> findAll(Integer categoryId, Integer userId, Filter filter, String search, Pageable pageable) {
        var query = context.select(getSelectColumns()).from(items)
                .leftJoin(favoriteItems).on(
                        userId != null ?
                        favoriteItems.USER_ID.eq(userId).and(favoriteItems.ITEM_ID.eq(items.ID)) :
                        DSL.falseCondition())
                .join(images).on(
                        images.ITEM_ID.eq(items.ID).and(images.IS_DEFAULT.isTrue()))
                .where(getConditions(categoryId, filter, search));

        return getPaginatedResult(query, pageable, ItemResponse.class);
    }

    private <T> Page<T> getPaginatedResult(SelectConditionStep<Record> query, Pageable pageable, Class<T> aClass) {
        var paginatedQuery = query
                .limit(pageable.getPageSize())
                .offset(pageable.getPageNumber() * pageable.getPageSize());

        List<T> result = paginatedQuery.fetchInto(aClass);

        int totalCount = context.fetchCount(paginatedQuery);
        return new PageImpl<>(result, pageable, totalCount);
    }

    private Condition getConditions(Integer categoryId, Filter filter, String search) {
        Condition condition = DSL.trueCondition();
        if (categoryId != null)
            condition = condition.and(items.CATEGORY_ID.eq(categoryId));

        if (filter != null)
            condition = condition.and(filter == Filter.NEW ?
                    items.CREATED_AT.greaterOrEqual(getDateLimit()) :
                    items.NUMBER_OF_PURCHASES.greaterOrEqual(20));

        if (search != null) {
            condition = DSL.lower(items.NAME).like("%" + search.toLowerCase() + "%")
                    .or(DSL.lower(items.DESCRIPTION).like("%" + search.toLowerCase() + "%"));
        }
        return condition;
    }

    private LocalDateTime getDateLimit() {
        return LocalDateTime.now().minus(7, ChronoUnit.DAYS);
    }

    private List<Field<?>> getSelectColumns() {
        return Arrays.asList(
                items.ID,
                items.NAME,
                items.PRICE,
                items.RATING,
                images.URL.as("imageUrl"),
                favoriteItems.ITEM_ID.isNotNull().as("isLiked")
        );
    }
}
