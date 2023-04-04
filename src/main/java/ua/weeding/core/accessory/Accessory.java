package ua.weeding.core.accessory;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.weeding.core.picture.Picture;

import java.math.BigDecimal;
import java.util.List;

@Data
@Document
public class Accessory {

    @Id
    private String id;
    private String name;
    private int price;
    private String brand;
    private String sizes;
    private String description;
    private boolean ifDiscount;
    private String type;

    List<String> pictures;



}
