package sample.jpa.helloshop.model;

import lombok.Getter;
import lombok.Setter;
import sample.jpa.helloshop.domain.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
    private String etc;
}
