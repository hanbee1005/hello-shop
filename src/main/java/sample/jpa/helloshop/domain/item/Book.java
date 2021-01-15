package sample.jpa.helloshop.domain.item;

import lombok.Getter;
import lombok.Setter;
import sample.jpa.helloshop.domain.item.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("B")
public class Book extends Item {
    private String author;
    private String isbn;
}
