package com.itacademy.service.dto;

import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.Resource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCommentDto {

    private Long id;
    private Person person;
    private Resource resource;
    private String text;
}
