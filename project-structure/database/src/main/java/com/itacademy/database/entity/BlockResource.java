package com.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor/*(onConstructor = @__(@Autowired))*/
@Entity
@DiscriminatorValue("block")
//@Component
public class BlockResource extends Resource {

    private Long id;
    private String block;

    public BlockResource(String resourceName, String foto, Heading heading, Category category, Person person,
                         Integer price, String text, String block) {
        super(resourceName, foto, heading, category, person, price, text);
        this.block = block;
    }

    public BlockResource(String resourceName, String foto, Heading heading, Category category, Person person,
                         Integer price, String text,Long id, String block) {
        super( resourceName, foto, heading, category, person, price, text);
        this.id = id;
        this.block = block;
    }
}
