package com.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = "resources")
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@EqualsAndHashCode(exclude = "id")
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "heading", schema = "flea_market")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "headings")
public class Heading implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "heading_name", nullable = false)
    private String headingName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "headings", cascade = CascadeType.PERSIST)
//    @JoinTable(name = "resource_heading", schema = "flea_market", joinColumns = @JoinColumn(name = "heading_id"),
//            inverseJoinColumns = @JoinColumn(name = "resource_id"))
    private Set<Resource> resources = new HashSet<>();

    public Heading(Long id, String headingName, Category category) {
        this.id = id;
        this.headingName = headingName;
        this.category = category;
    }

    public Heading(String headingName, Category category) {
        this.headingName = headingName;
        this.category = category;
    }
}