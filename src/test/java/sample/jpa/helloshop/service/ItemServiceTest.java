package sample.jpa.helloshop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sample.jpa.helloshop.Repository.ItemRepository;
import sample.jpa.helloshop.domain.item.Item;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;

    @Test
    void 상품_등록() {
        // Given
        Item item = new Item();
        item.setName("macbook");

        // When
        Long saveId = itemService.saveItem(item);

        // Then
        assertEquals(item, itemRepository.findOne(saveId));
    }

    @Test
    void 전체_상품_조회() {
        // Given
        Item item1 = new Item();
        item1.setName("iPhone");

        Item item2 = new Item();
        item2.setName("airPod");

        itemService.saveItem(item1);
        itemService.saveItem(item2);

        // When
        List<Item> findAll = itemService.findItems();

        //Then
        assertEquals(2, findAll.size());
    }

    @Test
    void 상품_조회() {
        // Given
        Item item = new Item();
        item.setName("macbook");

        Long saveId = itemService.saveItem(item);

        // When
        Item findOne = itemService.findOne(saveId);

        // Then
        assertEquals(findOne, item);
    }
}