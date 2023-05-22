package com.inai.arna.controller;

import com.inai.arna.dto.request.CardNumberRequest;
import com.inai.arna.dto.request.CreditCardRequest;
import com.inai.arna.dto.response.CreditCardResponse;
import com.inai.arna.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/protected/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("credit-cards")
    public List<CreditCardResponse> getCreditCards() {
        return userService.getCreditCards();
    }

    @PostMapping("credit-cards")
    public ResponseEntity<CreditCardResponse> saveCreditCard(@RequestBody CreditCardRequest creditCardRequest) {
        var response = userService.saveCreditCard(creditCardRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("credit-cards")
    public CreditCardResponse makeCardDefault(@RequestBody CardNumberRequest cardNumberRequest) {
        return userService.makeCardDefault(cardNumberRequest);
    }
}
