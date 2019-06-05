package com.itacademy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryFullDto {

    private Long id;
    private String categoryName;

    public CategoryFullDto(String categoryName) {
        this.categoryName = categoryName;
    }
}
