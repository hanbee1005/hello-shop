package sample.jpa.helloshop.domain;

import lombok.Getter;
import lombok.Setter;
import sample.jpa.helloshop.model.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public class Item extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;       // 이름
    private int price;         // 가격
    private int stockQuantity; // 재고 수량

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
