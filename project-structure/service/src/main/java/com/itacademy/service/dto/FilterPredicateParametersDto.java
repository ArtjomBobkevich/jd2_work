package com.itacademy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilterPredicateParametersDto {

    private String resource;
    private String category;
    private String price;
    private Integer offset;
    private Integer limit;
    private Integer constLimit;

    public FilterPredicateParametersDto(String resource, String category) {
        this.resource = resource;
        this.category = category;
    }
}