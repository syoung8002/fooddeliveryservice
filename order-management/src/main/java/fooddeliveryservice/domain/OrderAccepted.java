package fooddeliveryservice.domain;

import fooddeliveryservice.domain.*;
import fooddeliveryservice.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderAccepted extends AbstractEvent {

    private String orderId;
    private String restaurantId;
    private String userId;

    public OrderAccepted(Order aggregate) {
        super(aggregate);
    }

    public OrderAccepted() {
        super();
    }
}
//>>> DDD / Domain Event
