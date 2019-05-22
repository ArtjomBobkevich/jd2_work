package by.itacademy.database.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "heading", schema = "flea_market")
public class Heading extends BaseEntity<Long>{

    @Column(name = "heading_name",nullable = false)
    private String headingName;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
