package action.in.blog.service;

import action.in.blog.domain.CustomerOrder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderService {

    private final Map<String, List<CustomerOrder>> map = Map.of(
            "Sam", List.of(
                    CustomerOrder.create(UUID.randomUUID(), "Sam product 1"),
                    CustomerOrder.create(UUID.randomUUID(), "Sam product 2")
            )
    );

    public Flux<CustomerOrder> orderByClauseName(String name) {
        return Flux.fromIterable(
                        map.getOrDefault(name, Collections.emptyList())
                )
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(customer -> print(String.format("order for %s", name)));
    }

    private void print(String message) {
        System.out.printf("OrderService[%s]: {%s}\n", LocalDateTime.now(), message);
    }
}
