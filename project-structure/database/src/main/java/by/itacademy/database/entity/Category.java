package by.itacademy.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@Table(name = "category", schema = "flea_market")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", unique = true, nullable = false)
    private String categoryName;

    @Column(name = "foto")
    private String fotoUrl;


    public Category(String categoryName, String fotoUrl) {
        this.categoryName = categoryName;
        this.fotoUrl = fotoUrl;
    }
}
