package fooddeliveryservice.domain;

import fooddeliveryservice.domain.*;
import fooddeliveryservice.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private String orderId;
    private String restaurantId;
    private String userId;
    private String foodId;
    private Integer quantity;
    private String specialRequest;
    private String paymentMethod;
}
