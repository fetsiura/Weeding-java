package ua.weeding.core.token;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.UUID;

@Document
@Data
public class ConfirmationToken {
    @Id
    private String id;
    private String confirmationToken;
    private LocalDateTime expireTime;
    private String userId;

    public ConfirmationToken() {
    }

    public ConfirmationToken(String userId) {
        this.userId = userId;
        expireTime = LocalDateTime.now().plusHours(1L);
        confirmationToken = UUID.randomUUID().toString();
    }
}
