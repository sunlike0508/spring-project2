package hello.itemservice.domain.item;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();


    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }


    @Test
    void save() {
        Item item = new Item("itemA", 10000, 10);

        Item savedItem = itemRepository.save(item);

        Item findedItem = itemRepository.findById(savedItem.getId());

        assertThat(savedItem.getId()).isEqualTo(findedItem.getId());

    }


    @Test
    void findAll() {
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemA", 10000, 10);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> itemList = itemRepository.findAll();

        assertThat(itemList).hasSize(2);

    }


    @Test
    void update() {
        Item item1 = new Item("itemA", 10000, 10);
        Item savedItem = itemRepository.save(item1);

        itemRepository.update(savedItem.getId(), new Item("itemB", 20000, 30));

        Item findItem = itemRepository.findById(savedItem.getId());

        assertThat(findItem.getItemName()).isEqualTo("itemB");
        assertThat(findItem.getPrice()).isEqualTo(20000);
        assertThat(findItem.getQuantity()).isEqualTo(30);
    }


    @Test
    void clearStore() {
    }
}