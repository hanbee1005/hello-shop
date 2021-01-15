package sample.jpa.helloshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    private Date createdDate = new Date();  // 등록일
    private Date lastModifiedDate;  // 수정일
}
