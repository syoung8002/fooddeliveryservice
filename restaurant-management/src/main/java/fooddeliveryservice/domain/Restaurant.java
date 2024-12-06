package fooddeliveryservice.domain;

import fooddeliveryservice.RestaurantManagementApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Restaurant_table")
@Data
//<<< DDD / Aggregate Root
public class Restaurant {

    @Id
    private String restaurantId;

    private String status;

    public static RestaurantRepository repository() {
        RestaurantRepository restaurantRepository = RestaurantManagementApplication.applicationContext.getBean(
            RestaurantRepository.class
        );
        return restaurantRepository;
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

}
//>>> DDD / Aggregate Root
