package fooddeliveryservice.domain;

import fooddeliveryservice.OrderManagementApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
//<<< DDD / Aggregate Root
public class Order {

    @Id
    private String orderId;

    private String restaurantId;

    private String userId;

    private String foodId;

    private Integer quantity;

    private String specialRequest;

    private String paymentMethod;

    private String status;

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderManagementApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    //<<< Clean Arch / Port Method
    public void acceptOrder(AcceptOrderCommand acceptOrderCommand) {
        //implement business logic here:

        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void rejectOrder(RejectOrderCommand rejectOrderCommand) {
        //implement business logic here:

        OrderRejected orderRejected = new OrderRejected(this);
        orderRejected.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void pickupFood(PickupFoodCommand pickupFoodCommand) {
        //implement business logic here:

        FoodPickedUp foodPickedUp = new FoodPickedUp(this);
        foodPickedUp.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void deliverFood(DeliverFoodCommand deliverFoodCommand) {
        //implement business logic here:

        FoodDelivered foodDelivered = new FoodDelivered(this);
        foodDelivered.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
