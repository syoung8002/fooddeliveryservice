package fooddeliveryservice.domain;

import fooddeliveryservice.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class OrderAccepted extends AbstractEvent {

    private String orderId;
    private String restaurantId;
    private String userId;
}
