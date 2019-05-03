package by.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Person {

    private Long id;
    private String avatar;
    private String login;
    private String firstName;
    private String lastName;
    private String age;
    private String mail;
    private String password;
    private PersonRole personRole;
}
