package com.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "personList")
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor/*(onConstructor = @__(@Autowired))*/
@Builder
@Entity
//@Component
@Table(name = "person_role", schema = "flea_market")
public class PersonRole implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", unique = true, nullable = false)
    private String nameOfRole;

    @OneToMany(mappedBy = "personRole")
    private List<Person> personList = new ArrayList<>();

    public PersonRole(String nameOfRole) {
        this.nameOfRole = nameOfRole;
    }
}