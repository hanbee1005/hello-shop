package sample.jpa.helloshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.jpa.helloshop.Repository.ItemRepository;
import sample.jpa.helloshop.Repository.MemberRepository;
import sample.jpa.helloshop.Repository.OrderRepository;
import sample.jpa.helloshop.domain.Delivery;
import sample.jpa.helloshop.domain.Member;
import sample.jpa.helloshop.domain.Order;
import sample.jpa.helloshop.domain.OrderItem;
import sample.jpa.helloshop.domain.item.Item;

import java.util.List;

@Service
@Transactional
public class OrderService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(MemberRepository memberRepository, ItemRepository itemRepository, OrderRepository orderRepository) {
        this.memberRepository = memberRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * 주문
     * @param memberId
     * @param itemId
     * @param count
     * @return
     */
    public Long Order(Long memberId, Long itemId, int count) {
        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery(member.getAddress());
        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    /**
     * 주문 취소
     * @param orderId
     */
    public void cancelOrder(Long orderId) {
        // 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();
    }

    /**
     * 주문 검색
     * @param orderSearch
     * @return
     */
//    public List<Order> findOrders(OrderSearch orderSearch) {
//        return orderRepository.findAll(orderSearch);
//    }
}
