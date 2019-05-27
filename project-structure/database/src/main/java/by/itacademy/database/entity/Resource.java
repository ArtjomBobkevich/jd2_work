package by.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"comments", "personList", "storeBasketPerson"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Builder
@Entity
@Table(name = "resource", schema = "flea_market")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class Resource implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "resource_person", schema = "flea_market", joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id"))
    private List<Person> personList = new ArrayList<>();

    @OneToMany(mappedBy = "resource")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(mappedBy = "storeBasketResources", cascade = CascadeType.ALL)
    private List<Person> storeBasketPerson = new ArrayList<>();

    public Resource(String resourceName, String foto, Heading heading, Category category, Person person, Integer price, String text) {
        this.resourceName = resourceName;
        this.foto = foto;
        this.heading = heading;
        this.category = category;
        this.person = person;
        this.price = price;
        this.text = text;
    }
}
