package by.itacademy.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name = "store_basket", schema = "flea_market")
public class StoreBasket extends BaseEntity<Long> {

    @Column(name = "foto",nullable = false)
    private String foto;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToMany
    @JoinTable(name = "store_basket",schema = "flea_market",joinColumns = @JoinColumn(name = "resources_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    public List<Resource> resources = new ArrayList<>();
}
