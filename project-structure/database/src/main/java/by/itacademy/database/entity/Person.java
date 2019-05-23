package by.itacademy.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
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
    private Integer age;
    @Column(name = "mail", unique = true, nullable = false)
    private String mail;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "role")
    private PersonRole personRole;

    @ManyToMany
    @JoinTable (name = "resource_person",schema = "flea_market",joinColumns = @JoinColumn(name = "resources_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Resource> resources = new ArrayList<>();
}
