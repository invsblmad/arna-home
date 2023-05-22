package com.inai.arna.service.impl;

import com.inai.arna.exception.CreditCardExpiredException;
import com.inai.arna.model.card.CreditCard;
import com.inai.arna.service.CardValidationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service

public class CardValidationServiceImpl implements CardValidationService {
    @Override
    public boolean isCardValid(CreditCard creditCard) {
        if (isDateExpired(creditCard.getExpiryDate()))
            throw new CreditCardExpiredException("The card is expired");
        return true;
    }

    private boolean isDateExpired(LocalDate expiryDate) {
        return LocalDate.now().isAfter(expiryDate);
    }
}
