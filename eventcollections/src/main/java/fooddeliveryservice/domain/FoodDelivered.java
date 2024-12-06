package fooddeliveryservice.domain;

import fooddeliveryservice.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class FoodDelivered extends AbstractEvent {

    private String orderId;
    private String deliveryDriverId;
}
