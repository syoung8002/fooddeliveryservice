package fooddeliveryservice.infra;

import fooddeliveryservice.config.kafka.KafkaProcessor;
import fooddeliveryservice.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class EventCollectorViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private EventCollectorRepository eventCollectorRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_CREATE_1(
        @Payload OrderPlaced orderPlaced
    ) {
        try {
            if (!orderPlaced.validate()) return;

            // view 객체 생성
            EventCollector eventCollector = new EventCollector();
            // view 객체에 이벤트의 Value 를 set 함
            eventCollector.setType(orderPlaced.getEventType());
            eventCollector.setCorrelationKey(orderPlaced.getOrderId());
            eventCollector.setPayload(orderPlaced.toString());
            eventCollector.setTimestamp(orderPlaced.getTimestamp());
            // view 레파지 토리에 save
            eventCollectorRepository.save(eventCollector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderAccepted_then_CREATE_2(
        @Payload OrderAccepted orderAccepted
    ) {
        try {
            if (!orderAccepted.validate()) return;

            // view 객체 생성
            EventCollector eventCollector = new EventCollector();
            // view 객체에 이벤트의 Value 를 set 함
            eventCollector.setType(orderAccepted.getEventType());
            eventCollector.setCorrelationKey(orderAccepted.getOrderId());
            eventCollector.setPayload(orderAccepted.toString());
            eventCollector.setTimestamp(orderAccepted.getTimestamp());
            // view 레파지 토리에 save
            eventCollectorRepository.save(eventCollector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderRejected_then_CREATE_3(
        @Payload OrderRejected orderRejected
    ) {
        try {
            if (!orderRejected.validate()) return;

            // view 객체 생성
            EventCollector eventCollector = new EventCollector();
            // view 객체에 이벤트의 Value 를 set 함
            eventCollector.setType(orderRejected.getEventType());
            eventCollector.setCorrelationKey(orderRejected.getOrderId());
            eventCollector.setPayload(orderRejected.toString());
            eventCollector.setTimestamp(orderRejected.getTimestamp());
            // view 레파지 토리에 save
            eventCollectorRepository.save(eventCollector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenFoodPickedUp_then_CREATE_4(
        @Payload FoodPickedUp foodPickedUp
    ) {
        try {
            if (!foodPickedUp.validate()) return;

            // view 객체 생성
            EventCollector eventCollector = new EventCollector();
            // view 객체에 이벤트의 Value 를 set 함
            eventCollector.setType(foodPickedUp.getEventType());
            eventCollector.setCorrelationKey(foodPickedUp.getOrderId());
            eventCollector.setPayload(foodPickedUp.toString());
            eventCollector.setTimestamp(foodPickedUp.getTimestamp());
            // view 레파지 토리에 save
            eventCollectorRepository.save(eventCollector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenFoodDelivered_then_CREATE_5(
        @Payload FoodDelivered foodDelivered
    ) {
        try {
            if (!foodDelivered.validate()) return;

            // view 객체 생성
            EventCollector eventCollector = new EventCollector();
            // view 객체에 이벤트의 Value 를 set 함
            eventCollector.setType(foodDelivered.getEventType());
            eventCollector.setCorrelationKey(foodDelivered.getOrderId());
            eventCollector.setPayload(foodDelivered.toString());
            eventCollector.setTimestamp(foodDelivered.getTimestamp());
            // view 레파지 토리에 save
            eventCollectorRepository.save(eventCollector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
