package com.inai.arna.repository.custom.impl;

import com.inai.arna.dto.request.Filter;
import com.inai.arna.dto.response.ItemDetailsResponse;
import com.inai.arna.dto.response.ItemResponse;
import com.inai.arna.exception.NotFoundException;
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

    @Override
    public ItemDetailsResponse findById(Integer itemId, Integer userId) {
        var query = context.select(getItemDetailsColumns()).from(items)
                .leftJoin(favoriteItems).on(
                        userId != null ?
                        favoriteItems.USER_ID.eq(userId).and(favoriteItems.ITEM_ID.eq(items.ID)) :
                        DSL.falseCondition())
                .where(items.ID.eq(itemId));

        Record record = query.fetchOne();
        if (record == null)
            throw new NotFoundException("Item with id " + itemId + " is not found");
        return record.into(ItemDetailsResponse.class);
    }

    private List<Field<?>> getSelectColumns() {
        return Arrays.asList(
                items.ID,
                items.NAME,
                items.PRICE,
                items.RATING,
                favoriteItems.ITEM_ID.isNotNull().as("isLiked"),
                images.URL.as("imageUrl")
        );
    }

    private Condition getConditions(Integer categoryId, Filter filter, String search) {
        Condition condition = DSL.trueCondition();

        if (categoryId != null)
            condition = getByCategory(categoryId);
        if (filter != null)
            condition = getByFilter(condition, filter);
        if (search != null)
            condition = getBySearch(search);

        return condition;
    }

    private Condition getByCategory(Integer categoryId) {
        return DSL.trueCondition().and(items.CATEGORY_ID.eq(categoryId));
    }

    private Condition getByFilter(Condition previous, Filter filter) {
        LocalDateTime dateLimit = LocalDateTime.now().minus(7, ChronoUnit.DAYS);
        return previous.and(filter == Filter.NEW ?
                            items.CREATED_AT.greaterOrEqual(dateLimit) :
                            items.NUMBER_OF_PURCHASES.greaterOrEqual(20));
    }

    private Condition getBySearch(String search) {
        return DSL.lower(items.NAME).like("%" + search.toLowerCase() + "%")
                .or(DSL.lower(items.DESCRIPTION).like("%" + search.toLowerCase() + "%"));
    }

    private <T> Page<T> getPaginatedResult(SelectConditionStep<Record> query, Pageable pageable, Class<T> aClass) {
        var paginatedQuery = query
                .limit(pageable.getPageSize())
                .offset(pageable.getPageNumber() * pageable.getPageSize());

        List<T> result = paginatedQuery.fetchInto(aClass);

        int totalCount = context.fetchCount(paginatedQuery);
        return new PageImpl<>(result, pageable, totalCount);
    }

    private List<Field<?>> getItemDetailsColumns() {
        var columns = getSelectColumns();
        columns.set(columns.size() - 1, items.DESCRIPTION);
        return columns;
    }

}
