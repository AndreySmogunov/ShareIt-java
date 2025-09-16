import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import ru.practicum.gateway.client.UserClient;

import static org.junit.jupiter.api.Assertions.*;

@RestClientTest(UserClient.class)
public class UserClientTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RestTemplateBuilder builder;

    @Test
    public void testGetUserById() {
        UserClient client = new UserClient(builder);
        ResponseEntity<String> response = (ResponseEntity<String>) client.getUserById(1L);

        assertNotNull(response);
    }
}