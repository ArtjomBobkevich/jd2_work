package com.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@ToString(exclude = {"headings"})
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category", schema = "flea_market")
public class Category implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
