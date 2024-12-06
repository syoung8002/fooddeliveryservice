package fooddeliveryservice.domain;

import fooddeliveryservice.domain.*;
import fooddeliveryservice.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderRejected extends AbstractEvent {

    private String orderId;
    private String restaurantId;
    private String userId;

    public OrderRejected(Order aggregate) {
        super(aggregate);
    }

    public OrderRejected() {
        super();
    }
}
//>>> DDD / Domain Event
