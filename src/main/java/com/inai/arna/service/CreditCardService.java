package com.inai.arna.service;

import com.inai.arna.dto.request.CreditCardRequest;
import com.inai.arna.dto.response.CreditCardResponse;
import com.inai.arna.model.user.User;

import java.util.List;

public interface CreditCardService {
    List<CreditCardResponse> getAll(User user);
    CreditCardResponse save(User user, CreditCardRequest creditCardRequest);
    CreditCardResponse makeDefault(User user, String cardNumber);
}
