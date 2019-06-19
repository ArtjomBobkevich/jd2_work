package com.itacademy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResourceAddDto {

    private Long resourceId;
    private String login;

    public ResourceAddDto(Long resourceId) {
        this.resourceId = resourceId;
    }
}