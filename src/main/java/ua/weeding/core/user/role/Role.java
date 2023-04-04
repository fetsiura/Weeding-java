package ua.weeding.core.user.role;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "role")
public class Role {
    @Id
    private String id;
    private String name;
}
