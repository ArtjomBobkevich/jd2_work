package com.itacademy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleFullDto {

    private Long id;
    private String nameOfRole;

    public RoleFullDto(String nameOfRole) {
        this.nameOfRole = nameOfRole;
    }
}