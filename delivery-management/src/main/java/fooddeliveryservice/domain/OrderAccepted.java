package fooddeliveryservice.domain;

import fooddeliveryservice.domain.*;
import fooddeliveryservice.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderAccepted extends AbstractEvent {

    private String orderId;
    private String restaurantId;
    private String userId;
}
