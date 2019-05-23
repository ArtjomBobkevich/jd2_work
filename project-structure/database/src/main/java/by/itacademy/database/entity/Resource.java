package by.itacademy.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@ToString(exclude = {"comments","personList"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name = "resource", schema = "flea_market")
public class Resource extends BaseEntity<Long>{

    @Column(name = "resource_name", nullable = false)
    private String resourceName;
    @Column(name = "foto")
    private String foto;
    @OneToOne
    @JoinColumn(name = "heading_id")
    private Heading heading;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToOne
    @JoinColumn(name = "author")
    private Person person;
    @Column(name = "price")
    private Integer price;
    @Column(name = "text")
    private String text;

    @ManyToMany
    @JoinTable(name = "resource_person",schema = "flea_market",joinColumns = @JoinColumn(name = "person_id"),
    inverseJoinColumns = @JoinColumn(name = "resource_id"))
    private List<Person> personList = new ArrayList<>();

    @OneToMany(mappedBy = "resource")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(mappedBy = "resources")
    private List<StoreBasket> storeBaskets = new ArrayList<>();
}
