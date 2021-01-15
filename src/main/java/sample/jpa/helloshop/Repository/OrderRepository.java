package sample.jpa.helloshop.Repository;

import org.springframework.stereotype.Repository;
import sample.jpa.helloshop.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    EntityManager em;

    /**
     * 주문 등록
     * @param order
     */
    public void save(Order order) {
        em.persist(order);
    }

//    public List<Order> findAll(OrderSearch orderSearch) {
//
//    }

    /**
     * 주문 ID 로 조회
     * @param id
     * @return
     */
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }
}
