package sample.jpa.helloshop.model;

import lombok.Getter;
import lombok.Setter;
import sample.jpa.helloshop.domain.Item;

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
