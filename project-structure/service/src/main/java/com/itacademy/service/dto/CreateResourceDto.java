package com.itacademy.service.dto;

import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Heading;
import com.itacademy.database.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateResourceDto {

    private Long id;
    private String resourceName;
    private String foto;
    private Heading heading;
    private Category category;
    private Person person;
    private Integer price;
    private String text;
    private String block;
}
