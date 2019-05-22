package by.itacademy.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name = "store_basket", schema = "flea_market")
public class StoreBasket extends BaseEntity<Long> {

    @Column(name = "foto",nullable = false)
    private String foto;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

//    @ManyToOne /*почему?*/
    @OneToMany
    @JoinColumn(name = "resource_id")
    public Set<Resource> resources = new HashSet<>();
}
