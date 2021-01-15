package sample.jpa.helloshop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sample.jpa.helloshop.Repository.OrderRepository;
import sample.jpa.helloshop.domain.Member;
import sample.jpa.helloshop.domain.Order;
import sample.jpa.helloshop.domain.OrderSearch;
import sample.jpa.helloshop.domain.item.Book;
import sample.jpa.helloshop.domain.item.Item;
import sample.jpa.helloshop.exception.NotEnoughStockException;
import sample.jpa.helloshop.model.Address;
import sample.jpa.helloshop.model.OrderStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    void 주문() {
        // Given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        // When
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        // Then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        assertEquals(1, getOrder.getOrderItems().size());
        assertEquals(10000 * 2, getOrder.gerTotalPrice());
        assertEquals(8, item.getStockQuantity());
    }

    @Test
    void 상품_주문_재고수량초과() {
        // Given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 11;

        try {
            // When
            orderService.order(member.getId(), item.getId(), orderCount);

            fail("재고 수량 부족 예외가 발생해야 한다.");
        } catch (NotEnoughStockException e) {
            // Then
            assertEquals("need more stock", e.getMessage());
        }
    }

    @Test
    void 주문_취소() {
        // Given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        // When
        orderService.cancelOrder(orderId);

        // Then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals(10, item.getStockQuantity());
    }

    @Test
    void 주문_조회() {
        // Given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        // When
        OrderSearch orderSearch = new OrderSearch();
        orderSearch.setMemberName(member.getName());
        orderSearch.setOrderStatus(OrderStatus.ORDER);

        List<Order> findOrders = orderService.findOrders(orderSearch);

        // Then
        assertEquals(1, findOrders.size());
        for (Order order : findOrders) {
            assertEquals(OrderStatus.ORDER, order.getStatus());
            assertEquals(1, order.getOrderItems().size());
            assertEquals(10000 * 2, order.gerTotalPrice());
            assertEquals(8, item.getStockQuantity());

        }
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }
}