package com.inai.arna.service;

import com.inai.arna.dto.request.CardNumberRequest;
import com.inai.arna.dto.request.CreditCardRequest;
import com.inai.arna.dto.response.CreditCardResponse;

import java.util.List;

public interface UserService {
    Integer getAuthenticatedUserId();

    List<CreditCardResponse> getCreditCards();
    CreditCardResponse saveCreditCard(CreditCardRequest creditCardRequest);
    CreditCardResponse makeCardDefault(CardNumberRequest cardNumberRequest);
}
