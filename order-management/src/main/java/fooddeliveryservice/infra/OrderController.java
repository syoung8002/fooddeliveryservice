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
// @RequestMapping(value="/orders")
@Transactional
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @RequestMapping(
        value = "/orders/placeorder",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Order placeOrder(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody PlaceOrderCommand placeOrderCommand
    ) throws Exception {
        System.out.println("##### /order/placeOrder  called #####");
        Order order = new Order();
        order.placeOrder(placeOrderCommand);
        orderRepository.save(order);
        return order;
    }

    @RequestMapping(
        value = "/orders/acceptorder",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Order acceptOrder(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody AcceptOrderCommand acceptOrderCommand
    ) throws Exception {
        System.out.println("##### /order/acceptOrder  called #####");
        Order order = new Order();
        order.acceptOrder(acceptOrderCommand);
        orderRepository.save(order);
        return order;
    }

    @RequestMapping(
        value = "/orders/rejectorder",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Order rejectOrder(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RejectOrderCommand rejectOrderCommand
    ) throws Exception {
        System.out.println("##### /order/rejectOrder  called #####");
        Order order = new Order();
        order.rejectOrder(rejectOrderCommand);
        orderRepository.save(order);
        return order;
    }

    @RequestMapping(
        value = "/orders/pickupfood",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Order pickupFood(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody PickupFoodCommand pickupFoodCommand
    ) throws Exception {
        System.out.println("##### /order/pickupFood  called #####");
        Order order = new Order();
        order.pickupFood(pickupFoodCommand);
        orderRepository.save(order);
        return order;
    }

    @RequestMapping(
        value = "/orders/deliverfood",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Order deliverFood(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody DeliverFoodCommand deliverFoodCommand
    ) throws Exception {
        System.out.println("##### /order/deliverFood  called #####");
        Order order = new Order();
        order.deliverFood(deliverFoodCommand);
        orderRepository.save(order);
        return order;
    }
}
//>>> Clean Arch / Inbound Adaptor
