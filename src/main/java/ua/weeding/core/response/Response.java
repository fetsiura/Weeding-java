package ua.weeding.core.response;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
public class Response {
    @Id
    private String id;
    private String name;
    private int rating;
    private String description;
    private String date;
}
