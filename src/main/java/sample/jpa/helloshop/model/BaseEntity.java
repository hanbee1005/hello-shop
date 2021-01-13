package sample.jpa.helloshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    private Date createdDate;       // 등록일
    private Date lastModifiedDate;  // 수정일
}
