package com.inai.arna.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GroupedImageResponse {
    private String colorHex;
    private List<ImageResponse> images;
}
