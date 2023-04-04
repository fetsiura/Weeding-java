package ua.weeding.core.dress;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.weeding.core.picture.Picture;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Document
public class Dress {

    @Id
    private String id;
    private String name;
    private int price;
    private String colour;
    private String sizes;
    private String brand;
    private String description;
    private boolean ifDiscount;
    private boolean ifBestsellers;
    private boolean ifNew;
    private boolean ifAvailable;
    List<String> pictures;



}
