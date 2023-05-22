package com.inai.arna.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inai.arna.model.card.CardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CreditCardResponse {
    private String number;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "MM/yy")
    private LocalDate expiryDate;
    private CardType type;
    private boolean isDefault;
}
