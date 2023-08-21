package action.in.blog.service;

import action.in.blog.domain.CustomerOrder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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
            ),
            "Mike", List.of(
                    CustomerOrder.create(UUID.randomUUID(), "Mike product 1"),
                    CustomerOrder.create(UUID.randomUUID(), "Mike product 2"),
                    CustomerOrder.create(UUID.randomUUID(), "Mike product 3")
            )
    );

    public Flux<CustomerOrder> ordersByCustomerName(String name) {
        return Flux.fromIterable(map.getOrDefault(name, Collections.emptyList()));
    }
}
