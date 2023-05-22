package com.inai.arna.service.impl;

import com.inai.arna.dto.request.CardNumberRequest;
import com.inai.arna.dto.request.CreditCardRequest;
import com.inai.arna.dto.response.CreditCardResponse;
import com.inai.arna.model.user.User;
import com.inai.arna.service.AuthService;
import com.inai.arna.service.CreditCardService;
import com.inai.arna.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthService authService;
    private final CreditCardService creditCardService;

    @Override
    public Integer getAuthenticatedUserId() {
        User user = authService.getAuthenticatedUser();
        if (user != null)
            return user.getId();
        return null;
    }

    @Override
    public List<CreditCardResponse> getCreditCards() {
        User user = authService.getAuthenticatedUser();
        return creditCardService.getAll(user);
    }

    @Override
    public CreditCardResponse saveCreditCard(CreditCardRequest creditCardRequest) {
        User user = authService.getAuthenticatedUser();
        return creditCardService.save(user, creditCardRequest);
    }

    @Override
    public CreditCardResponse makeCardDefault(CardNumberRequest cardNumberRequest) {
        User user = authService.getAuthenticatedUser();
        return creditCardService.makeDefault(user, cardNumberRequest.getCardNumber());
    }
}
