package com.inai.arna.model.delivery;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "delivery_companies")
@Data
@NoArgsConstructor
@NamedEntityGraph(name = "DeliveryCompany.deliveries", attributeNodes = @NamedAttributeNode("deliveries"))
public class DeliveryCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "company")
    private List<Delivery> deliveries;
}
