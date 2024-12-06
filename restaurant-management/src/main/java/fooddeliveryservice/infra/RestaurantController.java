package fooddeliveryservice.infra;
import fooddeliveryservice.domain.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/restaurants")
@Transactional
public class RestaurantController {
    @Autowired
    RestaurantRepository restaurantRepository;

    @RequestMapping(value = "/restaurants/{id}/",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public Restaurant acceptOrder(@PathVariable(value = "id")  id, @RequestBody AcceptOrderCommand acceptOrderCommand, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /restaurant/acceptOrder  called #####");
            Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
            
            optionalRestaurant.orElseThrow(()-> new Exception("No Entity Found"));
            Restaurant restaurant = optionalRestaurant.get();
            restaurant.acceptOrder(acceptOrderCommand);
            
            restaurantRepository.save(restaurant);
            return restaurant;
            
    }
    @RequestMapping(value = "/restaurants/{id}/",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public Restaurant rejectOrder(@PathVariable(value = "id")  id, @RequestBody RejectOrderCommand rejectOrderCommand, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /restaurant/rejectOrder  called #####");
            Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
            
            optionalRestaurant.orElseThrow(()-> new Exception("No Entity Found"));
            Restaurant restaurant = optionalRestaurant.get();
            restaurant.rejectOrder(rejectOrderCommand);
            
            restaurantRepository.save(restaurant);
            return restaurant;
            
    }
}
//>>> Clean Arch / Inbound Adaptor
