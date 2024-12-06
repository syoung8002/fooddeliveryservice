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
// @RequestMapping(value="/restaurants")
@Transactional
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @RequestMapping(
        value = "/restaurants/acceptorder",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Restaurant acceptOrder(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody AcceptOrderCommand acceptOrderCommand
    ) throws Exception {
        System.out.println("##### /restaurant/acceptOrder  called #####");
        Restaurant restaurant = new Restaurant();
        restaurant.acceptOrder(acceptOrderCommand);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @RequestMapping(
        value = "/restaurants/rejectorder",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Restaurant rejectOrder(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RejectOrderCommand rejectOrderCommand
    ) throws Exception {
        System.out.println("##### /restaurant/rejectOrder  called #####");
        Restaurant restaurant = new Restaurant();
        restaurant.rejectOrder(rejectOrderCommand);
        restaurantRepository.save(restaurant);
        return restaurant;
    }
}
//>>> Clean Arch / Inbound Adaptor
