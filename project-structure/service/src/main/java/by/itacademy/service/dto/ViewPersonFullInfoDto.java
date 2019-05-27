package by.itacademy.service.dto;

import by.itacademy.database.entity.Identification;
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
}
