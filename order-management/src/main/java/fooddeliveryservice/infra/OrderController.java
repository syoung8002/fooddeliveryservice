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
// @RequestMapping(value="/orders")
@Transactional
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @RequestMapping(value = "/orders/{id}/",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public Order acceptOrder(@PathVariable(value = "id")  id, @RequestBody AcceptOrderCommand acceptOrderCommand, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /order/acceptOrder  called #####");
            Optional<Order> optionalOrder = orderRepository.findById(id);
            
            optionalOrder.orElseThrow(()-> new Exception("No Entity Found"));
            Order order = optionalOrder.get();
            order.acceptOrder(acceptOrderCommand);
            
            orderRepository.save(order);
            return order;
            
    }
    @RequestMapping(value = "/orders/{id}/",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public Order rejectOrder(@PathVariable(value = "id")  id, @RequestBody RejectOrderCommand rejectOrderCommand, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /order/rejectOrder  called #####");
            Optional<Order> optionalOrder = orderRepository.findById(id);
            
            optionalOrder.orElseThrow(()-> new Exception("No Entity Found"));
            Order order = optionalOrder.get();
            order.rejectOrder(rejectOrderCommand);
            
            orderRepository.save(order);
            return order;
            
    }
    @RequestMapping(value = "/orders/{id}/",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public Order pickupFood(@PathVariable(value = "id")  id, @RequestBody PickupFoodCommand pickupFoodCommand, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /order/pickupFood  called #####");
            Optional<Order> optionalOrder = orderRepository.findById(id);
            
            optionalOrder.orElseThrow(()-> new Exception("No Entity Found"));
            Order order = optionalOrder.get();
            order.pickupFood(pickupFoodCommand);
            
            orderRepository.save(order);
            return order;
            
    }
    @RequestMapping(value = "/orders/{id}/",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public Order deliverFood(@PathVariable(value = "id")  id, @RequestBody DeliverFoodCommand deliverFoodCommand, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /order/deliverFood  called #####");
            Optional<Order> optionalOrder = orderRepository.findById(id);
            
            optionalOrder.orElseThrow(()-> new Exception("No Entity Found"));
            Order order = optionalOrder.get();
            order.deliverFood(deliverFoodCommand);
            
            orderRepository.save(order);
            return order;
            
    }
}
//>>> Clean Arch / Inbound Adaptor
