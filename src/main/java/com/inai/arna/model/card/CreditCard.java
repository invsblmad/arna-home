package com.inai.arna.model.card;

import com.inai.arna.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "credit_cards")
@Data
@NoArgsConstructor
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private CardType type;
    private String number;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "expiry_date")
    private LocalDate expiryDate;
    @Column(name = "is_default")
    private boolean isDefault;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
