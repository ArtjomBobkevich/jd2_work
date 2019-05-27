package by.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("BLOCKRESOURCE")
public class BlockResource extends Resource {

    @Column(name = "block")
    private String block;

    public BlockResource(String resourceName, String foto, Heading heading, Category category, Person person,
                         Integer price, String text, String block) {
        super(resourceName, foto, heading, category, person, price, text);
        this.block = block;
    }
}
