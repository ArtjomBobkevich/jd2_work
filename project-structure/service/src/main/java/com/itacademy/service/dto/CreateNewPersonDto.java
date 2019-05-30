package com.itacademy.service.dto;

import com.itacademy.database.entity.Identification;
import com.itacademy.database.entity.PersonRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateNewPersonDto {

//    private Long id;
    private String avatar;
    private String login;
    private Identification identification;
    private Integer age;
    private String mail;
    private String password;
    private PersonRole personRole;
}
