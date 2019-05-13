package by.itacademy.database.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;


//@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Entity
@Table(name = "person_role", schema = "flea_market")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PersonRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role")
    private String nameOfRole;
}
