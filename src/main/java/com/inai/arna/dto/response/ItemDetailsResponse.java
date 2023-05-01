package com.inai.arna.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemDetailsResponse {
    private ItemDetailsView details;
    private int numberOfReviews;

}
