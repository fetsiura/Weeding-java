package ua.weeding.core.picture;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class Picture {

    @Id
    private String id;
    private String filename;
    private LocalDateTime created;
    private String placeOfUse;
    private String description;

}
