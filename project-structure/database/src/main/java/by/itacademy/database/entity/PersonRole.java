package by.itacademy.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@ToString(exclude = "personList")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Builder
@Entity
@Table(name = "person_role", schema = "flea_market")
public class PersonRole extends BaseEntity<Long> {

    @Column(name = "role", unique = true, nullable = false)
    private String nameOfRole;

    @OneToMany(mappedBy = "personRole")
    private List<Person> personList = new ArrayList<>();

    public PersonRole(String nameOfRole) {
        this.nameOfRole = nameOfRole;
    }
}
