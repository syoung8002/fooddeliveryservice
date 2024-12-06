package fooddeliveryservice.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PlaceOrderCommand {

    private String restaurantId;
    private String userId;
    private String foodId;
    private Integer quantity;
    private String specialRequest;
    private String paymentMethod;
}
