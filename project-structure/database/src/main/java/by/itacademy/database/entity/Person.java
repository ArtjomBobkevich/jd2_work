package by.itacademy.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "person", schema = "flea_market")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age", nullable = false)
    private String age;
    @Column(name = "mail", unique = true, nullable = false)
    private String mail;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role")
    @ManyToOne
    private PersonRole personRole;
}
