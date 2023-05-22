package com.inai.arna.mapper.impl;

import com.inai.arna.dto.request.CreditCardRequest;
import com.inai.arna.dto.response.CreditCardResponse;
import com.inai.arna.mapper.CreditCardMapper;
import com.inai.arna.model.card.CreditCard;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Service
public class CreditCardMapperImpl implements CreditCardMapper {

    @Override
    public CreditCard toEntity(CreditCardRequest creditCardRequest) {
        CreditCard card = new CreditCard();
        card.setNumber(creditCardRequest.getNumber());
        card.setFirstName(creditCardRequest.getFirstName());
        card.setLastName(creditCardRequest.getLastName());
        card.setDefault(creditCardRequest.isDefault());
        card.setType(creditCardRequest.getType());
        card.setExpiryDate(convertToDate(creditCardRequest.getExpiryDate()));
        return card;
    }

    @Override
    public CreditCardResponse toDto(CreditCard creditCard) {
        return new CreditCardResponse(
                creditCard.getNumber(),
                creditCard.getFirstName(),
                creditCard.getLastName(),
                creditCard.getExpiryDate(),
                creditCard.getType(),
                creditCard.isDefault()
        );
    }

    private LocalDate convertToDate(String expiryDate) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/yy");
        YearMonth ym = YearMonth.parse(expiryDate, fmt);
        return ym.atDay(1);
    }
}
