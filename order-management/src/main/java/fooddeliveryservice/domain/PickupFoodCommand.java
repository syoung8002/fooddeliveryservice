package fooddeliveryservice.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PickupFoodCommand {

    private String orderId;
}
