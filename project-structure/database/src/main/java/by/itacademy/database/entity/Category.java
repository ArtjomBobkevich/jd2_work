package by.itacademy.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "headings")
@EqualsAndHashCode(callSuper = true)
//@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Builder
@Entity
@Table(name = "category", schema = "flea_market")
public class Category extends BaseEntity<Long>{

    @Column(name = "category_name", unique = true, nullable = false)
    private String categoryName;

    @Column(name = "foto")
    private String fotoUrl;

    @OneToMany(mappedBy = "category")
    private List<Heading> headings = new ArrayList<>();

    public Category(String categoryName, String fotoUrl) {
        this.categoryName = categoryName;
        this.fotoUrl = fotoUrl;
    }
}
