package com.itacademy.service.dto;

import com.itacademy.database.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateHeadingDto {

    private Long id;
    private String headingName;
    private Category category;
}