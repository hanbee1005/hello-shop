package sample.jpa.helloshop.domain;

import lombok.Getter;
import lombok.Setter;
import sample.jpa.helloshop.model.Address;
import sample.jpa.helloshop.model.BaseEntity;
import sample.jpa.helloshop.model.DeliveryStatus;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Delivery extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public Delivery() {
    }

    public Delivery(Address address) {
        this.address = address;
    }
}