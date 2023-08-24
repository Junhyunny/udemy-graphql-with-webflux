package action.in.blog.service;

import action.in.blog.domain.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class CustomerService {

    private final Flux<Customer> flux = Flux.just(
            Customer.create(1, "Sam", 20, "Atlanta"),
            Customer.create(2, "Jake", 10, "Las vegas"),
            Customer.create(3, "Mike", 15, "Miami"),
            Customer.create(4, "John", 5, "Houston")
    );

    public Flux<Customer> customers() {
        return flux.delayElements(Duration.ofSeconds(1))
                .doOnNext(customer -> print(String.format("customer: %s", customer.getName())));
    }

    private void print(String message) {
        System.out.printf("CustomerService[%s]: {%s}\n", LocalDateTime.now(), message);
    }
}
