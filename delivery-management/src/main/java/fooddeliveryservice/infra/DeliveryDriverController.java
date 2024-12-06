package fooddeliveryservice.infra;

import fooddeliveryservice.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/deliveryDrivers")
@Transactional
public class DeliveryDriverController {

    @Autowired
    DeliveryDriverRepository deliveryDriverRepository;

    @RequestMapping(
        value = "/deliveryDrivers/{id}/deliverfood",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public DeliveryDriver deliverFood(
        @PathVariable(value = "id") String id,
        @RequestBody DeliverFoodCommand deliverFoodCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /deliveryDriver/deliverFood  called #####");
        Optional<DeliveryDriver> optionalDeliveryDriver = deliveryDriverRepository.findById(
            id
        );

        optionalDeliveryDriver.orElseThrow(() ->
            new Exception("No Entity Found")
        );
        DeliveryDriver deliveryDriver = optionalDeliveryDriver.get();
        deliveryDriver.deliverFood(deliverFoodCommand);

        deliveryDriverRepository.save(deliveryDriver);
        return deliveryDriver;
    }

    @RequestMapping(
        value = "/deliveryDrivers/updatelocation",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public DeliveryDriver updateLocation(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody UpdateLocationCommand updateLocationCommand
    ) throws Exception {
        System.out.println(
            "##### /deliveryDriver/updateLocation  called #####"
        );
        DeliveryDriver deliveryDriver = new DeliveryDriver();
        deliveryDriver.updateLocation(updateLocationCommand);
        deliveryDriverRepository.save(deliveryDriver);
        return deliveryDriver;
    }
}
//>>> Clean Arch / Inbound Adaptor
