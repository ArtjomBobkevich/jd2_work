package com.itacademy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCategoryDto {

    private Long id;
    private String categoryName;
    private String foto;
    private Long version;

    public CreateCategoryDto(String categoryName, String foto) {
        this.categoryName = categoryName;
        this.foto = foto;
    }
}
