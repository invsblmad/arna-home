package com.inai.arna.model.favorites;

import lombok.Data;

import java.io.Serializable;

@Data
public class FavoriteItemId implements Serializable {
    private Integer userId;
    private Integer itemId;
}
