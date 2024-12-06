package fooddeliveryservice.infra;

import fooddeliveryservice.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class OrderHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Order>> {

    @Override
    public EntityModel<Order> process(EntityModel<Order> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "//placeorder")
                .withRel("/placeorder")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "//acceptorder")
                .withRel("/acceptorder")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "//rejectorder")
                .withRel("/rejectorder")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "//pickupfood")
                .withRel("/pickupfood")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "//deliverfood")
                .withRel("/deliverfood")
        );

        return model;
    }
}
