package fooddeliveryservice.domain;

import fooddeliveryservice.domain.*;
import fooddeliveryservice.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
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

    public OrderPlaced(Order aggregate) {
        super(aggregate);
    }

    public OrderPlaced() {
        super();
    }
}
//>>> DDD / Domain Event
