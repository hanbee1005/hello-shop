package sample.jpa.helloshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zopcode;

    public Address() {
    }

    public Address(String city, String street, String zopcode) {
        this.city = city;
        this.street = street;
        this.zopcode = zopcode;
    }
}
