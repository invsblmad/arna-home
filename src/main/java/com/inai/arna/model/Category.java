package com.inai.arna.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@NamedEntityGraph(name = "Category.subCategories", attributeNodes = @NamedAttributeNode("subCategories"))
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "parentCategory")
    private List<Category> subCategories;
    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;
}

