package com.inai.arna.model.delivery;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "delivery")
@Data
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private DeliveryType type;
    private String time;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private DeliveryCompany company;
}
