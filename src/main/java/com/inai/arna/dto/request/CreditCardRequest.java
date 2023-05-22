package com.inai.arna.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inai.arna.model.card.CardType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCardRequest {
    private CardType type;
    private String number;
    private String firstName;
    private String lastName;
    private String securityCode;
    private String expiryDate;
    @JsonProperty
    private boolean isDefault;
}
