package com.inai.arna.model.category;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@NamedEntityGraph(name = "Room.itemCategories", attributeNodes = @NamedAttributeNode("itemCategories"))
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "room")
    private List<ItemCategory> itemCategories;
}
