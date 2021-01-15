package sample.jpa.helloshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sample.jpa.helloshop.domain.Member;
import sample.jpa.helloshop.domain.Order;
import sample.jpa.helloshop.domain.OrderSearch;
import sample.jpa.helloshop.domain.item.Item;
import sample.jpa.helloshop.model.OrderStatus;
import sample.jpa.helloshop.service.ItemService;
import sample.jpa.helloshop.service.MemberService;
import sample.jpa.helloshop.service.OrderService;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @Autowired
    public OrderController(OrderService orderService, MemberService memberService, ItemService itemService) {
        this.orderService = orderService;
        this.memberService = memberService;
        this.itemService = itemService;
    }

    /**
     * 주문 조회
     * @param memberName
     * @param orderStatus
     * @param model
     * @return
     */
    @GetMapping("/orders")
    public String list(@RequestParam(name = "memberName", required = false) String memberName,
                       @RequestParam(name = "orderStatus", required = false) OrderStatus orderStatus,
                       Model model) {
        OrderSearch orderSearch = new OrderSearch();
        orderSearch.setMemberName(memberName);
        orderSearch.setOrderStatus(orderStatus);

        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    /**
     * 주문
     * @param model
     * @return
     */
    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/order/{orderId}/cancel")
    public String cancel(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
