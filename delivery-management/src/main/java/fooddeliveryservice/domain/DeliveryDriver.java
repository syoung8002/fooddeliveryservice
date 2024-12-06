package fooddeliveryservice.domain;

import fooddeliveryservice.DeliveryManagementApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DeliveryDriver_table")
@Data
//<<< DDD / Aggregate Root
public class DeliveryDriver {

    @Id
    private String driverId;

    @Embedded
    private Address location;

    private String status;

    @PrePersist
    public void onPrePersist() {}

    public static DeliveryDriverRepository repository() {
        DeliveryDriverRepository deliveryDriverRepository = DeliveryManagementApplication.applicationContext.getBean(
            DeliveryDriverRepository.class
        );
        return deliveryDriverRepository;
    }

    //<<< Clean Arch / Port Method
    public void deliverFood(DeliverFoodCommand deliverFoodCommand) {
        //implement business logic here:

        FoodDelivered foodDelivered = new FoodDelivered(this);
        foodDelivered.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void updateLocation(UpdateLocationCommand updateLocationCommand) {
        //implement business logic here:

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
