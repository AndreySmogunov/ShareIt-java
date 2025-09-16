import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.practicum.item.entity.Item;
import ru.practicum.item.service.ItemService;
import ru.practicum.user.entity.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ItemServiceIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ItemService itemService;

    @Test
    public void testGetUserItems() {
        User user = new User("John Doe", "john@example.com");
        entityManager.persist(user);
        entityManager.flush();

        Item item1 = new Item("Laptop", "Powerful laptop", true, user);
        Item item2 = new Item("Mouse", "Wireless mouse", true, user);

        entityManager.persist(item1);
        entityManager.persist(item2);
        entityManager.flush();

        List<Item> items = itemService.getAllItemsByUser(user.getId());

        assertNotNull(items);
        assertEquals(2, items.size());
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
    }
}