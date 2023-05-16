package com.inai.arna.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageResponse {
    private String id;
    private String url;
    private String colorHex;
    private boolean isDefault;
}
