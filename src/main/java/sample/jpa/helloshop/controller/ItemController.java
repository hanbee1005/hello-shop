package sample.jpa.helloshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sample.jpa.helloshop.domain.item.Book;
import sample.jpa.helloshop.service.ItemService;

@Controller
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items/new")
    public String createForm() {
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(Book item) {
        itemService.saveItem(item);
        return "redirect:/";
    }
}
