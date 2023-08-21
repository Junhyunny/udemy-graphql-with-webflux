package action.in.blog.service;

import action.in.blog.domain.Customer;
import action.in.blog.domain.CustomerOrder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

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

    public Flux<List<CustomerOrder>> ordersByCustomerNames(List<String> names) {
        return Flux.fromIterable(names)
                // .map(name -> map.getOrDefault(name, Collections.emptyList()))
                // .flatMap(this::fetchOrders) // error - The size of the promised values MUST be the same size as the key list
                // .flatMap(
                //         name -> this.fetchOrders(name)
                //                 .defaultIfEmpty(Collections.emptyList())
                // ) // error - flatMap method works parallel - mismatching data, concurrency issue
                .flatMapSequential(
                        name -> this.fetchOrders(name)
                                .defaultIfEmpty(Collections.emptyList())
                );
    }

    private Mono<List<CustomerOrder>> fetchOrders(String name) {
        return Mono.justOrEmpty(map.get(name))
                .delayElement(Duration.ofMillis(ThreadLocalRandom.current().nextInt(0, 1000)));
    }

    public Mono<Map<Customer, List<CustomerOrder>>> fetchOrdersAsMap(List<Customer> customers) {
        return Flux.fromIterable(customers)
                .map(customer ->
                        Tuples.of(
                                customer,
                                map.getOrDefault(customer.getName(), Collections.emptyList())
                        )
                )
                .collectMap(Tuple2::getT1, Tuple2::getT2);
    }
}
