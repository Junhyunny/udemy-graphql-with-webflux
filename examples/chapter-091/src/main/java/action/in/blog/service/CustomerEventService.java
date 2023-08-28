package action.in.blog.service;

import action.in.blog.domain.CustomerEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class CustomerEventService {

    private final Sinks.Many<CustomerEvent> sink = Sinks.many()
            .multicast()
            .onBackpressureBuffer();
    private final Flux<CustomerEvent> flux = sink.asFlux().cache();

    public void emitEvent(CustomerEvent customerEvent) {
        sink.tryEmitNext(customerEvent);
    }

    public Flux<CustomerEvent> subscribe() {
        return flux;
    }
}
