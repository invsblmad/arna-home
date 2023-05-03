package com.inai.arna.util;

import com.inai.arna.dto.request.Filter;
import com.inai.arna.model.Item;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class ItemSpecification {
    public static Specification<Item> getSpecs(Filter filter, String search) {
        Specification<Item> spec = null;
        if (filter != null)
            spec = getByFilter(filter);

        if (search != null)
            spec = getBySearch(search);
        return spec;
    }

    private static Specification<Item> getByFilter(Filter filter) {
        LocalDateTime dateLimit = LocalDateTime.now().minus(7, ChronoUnit.DAYS);
        return (root, query, cb) -> {
            if (filter.equals(Filter.NEW))
                return cb.greaterThanOrEqualTo(root.get("createdAt"), dateLimit);
            else
                return cb.greaterThanOrEqualTo(root.get("numberOfPurchases"), 10);
        };
    }

    private static Specification<Item> getBySearch(String search) {
        return (root, query, cb) -> {
            Predicate onName = cb.like(cb.lower(root.get("name")), "%" + search.toLowerCase() + "%");
            Predicate onDescription = cb.like(cb.lower(root.get("description")), "%" + search.toLowerCase() + "%");
            return cb.or(onName, onDescription);
        };
    }
}
