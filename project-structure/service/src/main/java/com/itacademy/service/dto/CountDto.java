package com.itacademy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountDto {

    private Integer page;
    private Integer prevPage;

    public CountDto(Integer page) {
        this.page = page;
    }
}