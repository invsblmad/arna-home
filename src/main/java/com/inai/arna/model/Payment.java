package com.inai.arna.model;

import com.inai.arna.model.card.CreditCard;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal amount;
    @Column(name = "is_successful")
    private boolean isSuccessful;
    @Column(name = "created_at", columnDefinition = "TIMESTAMP(0) WITHOUT TIME ZONE")
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "credit_card_id", nullable = false)
    private CreditCard creditCard;
}
