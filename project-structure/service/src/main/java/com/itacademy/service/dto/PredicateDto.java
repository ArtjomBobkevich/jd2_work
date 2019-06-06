package com.itacademy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PredicateDto {

    private String resource;
    private String category;
    private Integer price;

    public PredicateDto(String resource, String category) {
        this.resource = resource;
        this.category = category;
    }
}
