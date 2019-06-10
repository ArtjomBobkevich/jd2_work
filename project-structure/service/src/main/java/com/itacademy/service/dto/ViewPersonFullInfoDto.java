package com.itacademy.service.dto;

import com.itacademy.database.entity.Identification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewPersonFullInfoDto {

    private Long id;
    private String avatar;
    private String login;
    private Identification identification;
    private Integer age;
    private String mail;
    private String password;
    private String personRole;

    public ViewPersonFullInfoDto(String avatar, String login, Identification identification, Integer age, String mail, String password, String personRole) {
        this.avatar = avatar;
        this.login = login;
        this.identification = identification;
        this.age = age;
        this.mail = mail;
        this.password = password;
        this.personRole = personRole;
    }
}