import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.practicum.gateway.dto.BookingRequest;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingRequestJsonTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSerializeAndDeserialize() throws Exception {
        // Arrange
        BookingRequest request = new BookingRequest();
        request.setItemId(1L);
        request.setStart(LocalDateTime.of(2025, 1, 1, 10, 0));
        request.setEnd(LocalDateTime.of(2025, 1, 1, 12, 0));

        // Act
        String json = objectMapper.writeValueAsString(request);
        BookingRequest deserialized = objectMapper.readValue(json, BookingRequest.class);

        // Assert
        assertThat(deserialized).isEqualToComparingFieldByField(request);
    }
}