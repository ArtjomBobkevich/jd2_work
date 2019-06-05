package com.itacademy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeadingFullDto {

    private Long id;
    private String headingName;
    private String category;

    public HeadingFullDto(String headingName, String category) {
        this.headingName = headingName;
        this.category = category;
    }
}
