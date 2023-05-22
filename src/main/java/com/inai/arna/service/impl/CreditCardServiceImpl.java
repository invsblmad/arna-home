package com.inai.arna.service.impl;

import com.inai.arna.dto.request.CreditCardRequest;
import com.inai.arna.dto.response.CreditCardResponse;
import com.inai.arna.exception.CreditCardNotValidException;
import com.inai.arna.exception.NotFoundException;
import com.inai.arna.mapper.CreditCardMapper;
import com.inai.arna.model.card.CreditCard;
import com.inai.arna.model.user.User;
import com.inai.arna.repository.CreditCardRepository;
import com.inai.arna.service.CardValidationService;
import com.inai.arna.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final CreditCardMapper creditCardMapper;
    private final CardValidationService cardValidationService;

    @Override
    public List<CreditCardResponse> getAll(User user) {
        Integer userId = user.getId();
        return creditCardRepository.findByUserId(userId);
    }

    @Override
    public CreditCardResponse save(User user, CreditCardRequest creditCardRequest) {
        CreditCard creditCard = creditCardMapper.toEntity(creditCardRequest);

        if (cardValidationService.isCardValid(creditCard)) {
            creditCard.setUser(user);
            creditCardRepository.save(creditCard);
            return creditCardMapper.toDto(creditCard);
        } else {
            throw new CreditCardNotValidException("The card is not valid");
        }
    }

    @Override
    public CreditCardResponse makeDefault(User user, String cardNumber) {
        removeOldDefault(user);

        CreditCard creditCard = findByUserAndNumber(user, cardNumber);
        creditCard.setDefault(true);

        creditCardRepository.save(creditCard);
        return creditCardMapper.toDto(creditCard);
    }

    private CreditCard findByUserAndNumber(User user, String cardNumber) {
        return creditCardRepository.findByUserAndNumber(user, cardNumber).orElseThrow(
                () -> new NotFoundException("The card with " + cardNumber + " is not found")
        );
    }

    private void removeOldDefault(User user) {
        var creditCard = creditCardRepository.findByUserAndIsDefault(user, true);
        if (creditCard.isPresent()) {
            creditCard.get().setDefault(false);
            creditCardRepository.save(creditCard.get());
        }
    }

}
