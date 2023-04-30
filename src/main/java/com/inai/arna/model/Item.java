package com.inai.arna.model;

import com.inai.arna.model.category.ItemCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    private BigDecimal price;
    private BigDecimal rating;
    @Column(name = "amount_in_stock")
    private int amountInStock;
    @Column(name = "number_of_purchases")
    private int numberOfPurchases;
    @Column(name = "created_at", columnDefinition = "TIMESTAMP(0) WITHOUT TIME ZONE")
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ItemCategory category;
}
