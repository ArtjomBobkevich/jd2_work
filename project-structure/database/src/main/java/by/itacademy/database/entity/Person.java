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
public class Person extends BaseEntity <Long> {

    @Column(name = "avatar")
    private String avatar;
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Embedded
    private Identification identification;
    @Column(name = "age", nullable = false)
    private String age;
    @Column(name = "mail", unique = true, nullable = false)
    private String mail;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "role")
    private PersonRole personRole;
}
