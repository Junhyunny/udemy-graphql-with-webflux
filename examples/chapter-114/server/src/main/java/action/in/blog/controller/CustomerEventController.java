package action.in.blog.controller;

import action.in.blog.domain.CustomerEvent;
import action.in.blog.service.CustomerEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Controller
public class CustomerEventController {

    private final CustomerEventService customerEventService;

    @SubscriptionMapping
    public Flux<CustomerEvent> customerEvents() {
        return customerEventService.subscribe();
    }
}
