package com.inai.arna.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private List<OrderItemRequest> orderItem;
    private String address;
    private String cardNumber;
    private int deliveryId;
}
