package fooddeliveryservice.domain;

import fooddeliveryservice.domain.*;
import fooddeliveryservice.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class FoodDelivered extends AbstractEvent {

    private String orderId;
    private String deliveryDriverId;

    public FoodDelivered(DeliveryDriver aggregate) {
        super(aggregate);
    }

    public FoodDelivered() {
        super();
    }
}
//>>> DDD / Domain Event
