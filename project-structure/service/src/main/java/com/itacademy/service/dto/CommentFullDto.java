package com.itacademy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentFullDto {

    private Long id;
    private String person;
    private String resource;
    private String comment;

    public CommentFullDto(String person, String resource, String comment) {
        this.person = person;
        this.resource = resource;
        this.comment = comment;
    }
}
