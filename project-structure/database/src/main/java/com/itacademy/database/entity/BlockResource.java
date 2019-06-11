package com.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("block")
public class BlockResource extends Resource {

    private String block;



    public BlockResource(Long id ,String resourceName, String foto, Category category, Person person,
                         Integer price, String text, String block) {
        super(id, resourceName, foto, category, person, price, text);
        this.block = block;
    }

    public BlockResource( Set<Heading> headings) {
        super( headings);

    }

    public BlockResource(String resourceName, String foto, Category category, Person person, Integer price, String text, String block) {
        super(resourceName, foto, category, person, price, text);
        this.block = block;
    }
}