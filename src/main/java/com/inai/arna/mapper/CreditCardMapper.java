package com.inai.arna.mapper;

import com.inai.arna.dto.request.CreditCardRequest;
import com.inai.arna.dto.response.CreditCardResponse;
import com.inai.arna.model.card.CreditCard;

public interface CreditCardMapper {
    CreditCard toEntity(CreditCardRequest creditCardRequest);
    CreditCardResponse toDto(CreditCard creditCard);
}
