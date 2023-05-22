package com.inai.arna.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    private int itemId;
    private int amount;
}
