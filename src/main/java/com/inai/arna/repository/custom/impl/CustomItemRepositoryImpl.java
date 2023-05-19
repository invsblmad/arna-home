package com.inai.arna.repository.custom.impl;

import com.inai.arna.dto.request.FilterRequest;
import com.inai.arna.dto.response.ItemDetailsResponse;
import com.inai.arna.dto.response.ItemResponse;
import com.inai.arna.exception.NotFoundException;
import com.inai.arna.jooq.model.Tables;
import com.inai.arna.jooq.model.tables.FavoriteItems;
import com.inai.arna.jooq.model.tables.Images;
import com.inai.arna.jooq.model.tables.ItemCategories;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomItemRepositoryImpl implements CustomItemRepository {
    private final DSLContext context;
    private Items items;
    private Images images;
    private FavoriteItems favoriteItems;
    private ItemCategories itemCategories;

    @PostConstruct
    private void init() {
        items = Tables.ITEMS;
        images = Tables.IMAGES;
        favoriteItems = Tables.FAVORITE_ITEMS;
        itemCategories = Tables.ITEM_CATEGORIES;
    }
    @Override
    public Page<ItemResponse> findAll(Integer roomId, Integer categoryId, Integer userId, FilterRequest filter,
                                      String search, Pageable pageable) {

        var query = context.select(getSelectColumns()).from(items)
                .join(itemCategories).on(itemCategories.ID.eq(items.CATEGORY_ID))
                .leftJoin(favoriteItems).on(
                        userId != null ?
                        favoriteItems.USER_ID.eq(userId).and(favoriteItems.ITEM_ID.eq(items.ID)) :
                        DSL.falseCondition())
                .join(images).on(
                        images.ITEM_ID.eq(items.ID).and(images.IS_DEFAULT.isTrue()))
                .where(getConditions(roomId, categoryId, filter, search))
                .orderBy(getSortFields(pageable.getSort()));

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

    @Override
    public Page<ItemResponse> findUserFavorites(Integer userId, String search, Pageable pageable) {
        var query = context.select(getSelectColumns()).from(items)
                .leftJoin(favoriteItems).on(
                        favoriteItems.ITEM_ID.eq(items.ID))
                .join(images).on(
                        images.ITEM_ID.eq(items.ID).and(images.IS_DEFAULT.isTrue()))
                .where(favoriteItems.USER_ID.eq(userId).and(
                        search != null ? getBySearch(search) : DSL.trueCondition()
                ));

        return getPaginatedResult(query, pageable, ItemResponse.class);
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

    private Condition getConditions(Integer roomId, Integer categoryId, FilterRequest filter, String search) {
        Condition condition = DSL.trueCondition();

        if (roomId != null) {
            if (categoryId != null)
                condition = getByCategory(roomId, categoryId);

            else if (filter != null)
                condition = getByFilter(roomId, filter);
        }
        else if (search != null)
            condition = getBySearch(search);

        return condition;
    }

    private Condition getByCategory(Integer roomId, Integer categoryId) {
        return itemCategories.ROOM_ID.eq(roomId).and(itemCategories.CATEGORY_ID.eq(categoryId));
    }

    private Condition getByFilter(Integer roomId, FilterRequest filter) {
        LocalDateTime dateLimit = LocalDateTime.now().minus(7, ChronoUnit.DAYS);
        return itemCategories.ROOM_ID.eq(roomId)
                .and(filter == FilterRequest.NEW ?
                        items.CREATED_AT.greaterOrEqual(dateLimit) :
                        items.NUMBER_OF_PURCHASES.greaterOrEqual(20));
    }

    private Condition getBySearch(String search) {
        return DSL.lower(items.NAME).like("%" + search.toLowerCase() + "%")
                .or(DSL.lower(items.DESCRIPTION).like("%" + search.toLowerCase() + "%"));
    }
    
    private List<SortField<?>> getSortFields(Sort sort) {
        return sort
                .stream()
                .map(s -> {
                    Field<?> field = DSL.field(s.getProperty());
                    return s.isAscending() ? field.asc() : field.desc();
                })
                .collect(Collectors.toList());
    }

    private <T> Page<T> getPaginatedResult(SelectSeekStepN<Record> query, Pageable pageable, Class<T> aClass) {
        var paginatedQuery = query
                .limit(pageable.getPageSize())
                .offset(pageable.getPageNumber() * pageable.getPageSize());

        List<T> result = paginatedQuery.fetchInto(aClass);

        int totalCount = context.fetchCount(paginatedQuery);
        return new PageImpl<>(result, pageable, totalCount);
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
