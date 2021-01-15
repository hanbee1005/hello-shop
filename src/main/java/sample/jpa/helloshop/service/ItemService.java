package sample.jpa.helloshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.jpa.helloshop.Repository.ItemRepository;
import sample.jpa.helloshop.domain.item.Item;

import java.util.List;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * 상품 등록
     * @param item
     */
    public Long saveItem(Item item) {
        itemRepository.save(item);
        return item.getId();
    }

    /**
     * 전체 상품 조회
     * @return
     */
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    /**
     * 아이템 ID 로 조회
     * @param itemId
     * @return
     */
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
