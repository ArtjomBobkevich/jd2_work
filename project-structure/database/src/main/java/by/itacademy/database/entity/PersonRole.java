package by.itacademy.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@Table(name = "person_role", schema = "flea_market")
public class PersonRole extends BaseEntity<Long> {

    @Column(name = "role", unique = true, nullable = false)
    private String nameOfRole;


    public PersonRole(String nameOfRole) {
        this.nameOfRole = nameOfRole;
    }
}
