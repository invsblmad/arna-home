package com.inai.arna.controller;

import com.inai.arna.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/protected/orders")
@RequiredArgsConstructor
public class OrderController {
    OrderService orderService;



}
