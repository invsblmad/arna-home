package com.inai.arna.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageRequest {
    private String colorHex;
    @JsonProperty
    private boolean isDefault;
}
