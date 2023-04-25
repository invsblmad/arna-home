package com.inai.arna.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@Data
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String path;
    private String colorHex;
    @Column(name = "is_default")
    private boolean isDefault;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
}
