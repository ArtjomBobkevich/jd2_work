package com.itacademy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResourceFullDto {

    private Long id;
    private String resourceName;
    private String foto;
    private String category;
    private String person;
    private Integer price;
    private String text;
    private String block;

    public ResourceFullDto(String resourceName, String foto, String category, String person, Integer price, String text, String block) {
        this.resourceName = resourceName;
        this.foto = foto;
        this.category = category;
        this.person = person;
        this.price = price;
        this.text = text;
        this.block = block;
    }
}