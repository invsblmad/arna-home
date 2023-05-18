package com.inai.arna.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageDetailsResponse {
    private String id;
    private String url;
    private String colorHex;
    private boolean isDefault;
}
