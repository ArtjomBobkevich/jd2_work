package by.itacademy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewPersonFullInfoDto {

    private String login;
    private String firstName;
    private String lastName;
    private String age;
    private String mail;
    private String password;
    private String personRole;
}
