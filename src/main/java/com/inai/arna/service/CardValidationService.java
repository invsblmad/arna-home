package com.inai.arna.service;

import com.inai.arna.model.card.CreditCard;

public interface CardValidationService {
    boolean isCardValid(CreditCard creditCard);
}
